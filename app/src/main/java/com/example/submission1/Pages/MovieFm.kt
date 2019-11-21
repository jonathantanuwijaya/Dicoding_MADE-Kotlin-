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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.Adapter.MovieShow
import com.example.submission1.base.BaseFragment
import com.example.submission1.model.MovieRes
import com.example.submission1.utils.Constans
import com.example.submission1.viewmodel.MovieVM
import kotlinx.android.synthetic.main.fragment_movies.*

class MovieFm : BaseFragment<MovieVM>() {

    private lateinit var show: MovieShow

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView(), R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            viewmodel.getMovie().observe(this, setValues)
        }

        show = MovieShow { movie ->
            val `in` = Intent(contextView(), DetailMovieActivity::class.java)
            `in`.putExtra(Constans.INTENT_DATA, movie)
            startActivity(`in`)
        }

        rvlistMovie.layoutManager = LinearLayoutManager(contextView())
        rvlistMovie.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistMovie.adapter = show
        viewmodel.getMovie().observe(this, setValues)
    }

    private val setValues = Observer<MovieRes> {
        it.results?.let { it2 -> show.setItem(it2) }
    }

    override fun initViewModel(): MovieVM {
        val vm = ViewModelProviders.of(this).get(MovieVM::class.java)
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
