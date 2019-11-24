package com.example.submission1.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.adapter.MovieShowAdapter
import com.example.submission1.base.BaseFragment
import com.example.submission1.model.FilmType
import com.example.submission1.model.MovieRes
import com.example.submission1.pages.DetailMovieActivity
import com.example.submission1.utils.Constant
import com.example.submission1.viewmodel.TVShowVM
import kotlinx.android.synthetic.main.main_fragment.*


class TVShowFm : BaseFragment<TVShowVM>() {
    private lateinit var showAdapter: MovieShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView(), R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            viewmodel?.getTVShow()?.observe(this, setTVShow)
        }

        showAdapter = MovieShowAdapter {
            val intent = Intent(contextView(), DetailMovieActivity::class.java)
            intent.putExtra(Constant.INTENT_DATA, it)
            intent.putExtra(Constant.FRAGMENT_DATA,FilmType.TVSHOW)
            startActivity(intent)
        }

        rvlistMovie.layoutManager = LinearLayoutManager(contextView())
        rvlistMovie.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistMovie.adapter = showAdapter
        viewmodel?.getTVShow()?.observe(this, setTVShow)
    }

    private val setTVShow = Observer<MovieRes> {
        it.results?.let { it1 -> showAdapter.setItem(it1) }
    }

    override fun initViewModel(): TVShowVM {
        val vm = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(TVShowVM::class.java)
        vm.setupView(this)
        return vm
    }

    override fun onShowProgressbar() {
        swiperefresh.isRefreshing = true
    }

    override fun onHideProgressbar() {
        swiperefresh.isRefreshing = false
    }

}