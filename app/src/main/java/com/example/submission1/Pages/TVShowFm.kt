package com.example.submission1.Pages

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.submission1.R
import com.example.submission1.Adapter.MovieShow
import com.example.submission1.base.BaseFragment
import com.example.submission1.model.MovieRes
import com.example.submission1.utils.Constans
import com.example.submission1.viewmodel.TVShowVM
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TVShowFm : BaseFragment<TVShowVM>() {
    private lateinit var show: MovieShow

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swiperefreshtv.setColorSchemeColors(ContextCompat.getColor(contextView(), R.color.colorAccent))
        swiperefreshtv.setOnRefreshListener {
            viewmodel.getTVShow().observe(this, setTVShow)
        }

        show = MovieShow {
            val intent = Intent(contextView(), DetailMovieActivity::class.java)
            intent.putExtra(Constans.INTENT_DATA, it)
            startActivity(intent)
        }

        rvlistTVShows.layoutManager = GridLayoutManager(contextView(), 2)
        rvlistTVShows.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistTVShows.adapter = show
        viewmodel.getTVShow().observe(this, setTVShow)
    }

    private val setTVShow = Observer<MovieRes> {
        it.results?.let { it1 -> show.setItem(it1) }
    }

    override fun initViewModel(): TVShowVM {
        val vm = ViewModelProviders.of(this).get(TVShowVM::class.java)
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
