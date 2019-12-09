package com.example.submission1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
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
import com.example.submission1.viewmodel.MovieVM
import kotlinx.android.synthetic.main.main_fragment.*

class MovieFragment : BaseFragment<MovieVM>() {

    private lateinit var showAdapter: MovieShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swiperefresh.setColorSchemeColors(ContextCompat.getColor(contextView(), R.color.colorAccent))
        swiperefresh.setOnRefreshListener {
            viewmodel?.getMovie()?.observe(viewLifecycleOwner, setValues)
        }

        showAdapter = MovieShowAdapter { movie ->
            val intent = Intent(contextView(), DetailMovieActivity::class.java)
            intent.putExtra(Constant.INTENT_DATA, movie)
            intent.putExtra(Constant.FRAGMENT_DATA,FilmType.MOVIE)
            startActivity(intent)
        }

        rvlistMovie.layoutManager = LinearLayoutManager(contextView())
        rvlistMovie.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistMovie.adapter = showAdapter
        viewmodel?.getMovie()?.observe(viewLifecycleOwner, setValues)
    }

    private val setValues = Observer<MovieRes> {
        it.results?.let { it2 -> showAdapter.setItem(it2) }
    }


    override fun initViewModel(): MovieVM {
        return ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MovieVM::class.java).apply {
            setupView(this@MovieFragment)
        }

    }

    override fun onShowProgressbar() {
        swiperefresh?.isRefreshing = true
    }

    override fun onHideProgressbar() {
        swiperefresh?.isRefreshing = false
    }
}
