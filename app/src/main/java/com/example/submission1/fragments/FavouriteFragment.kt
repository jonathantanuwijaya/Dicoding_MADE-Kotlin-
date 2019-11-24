package com.example.submission1.fragments

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
import com.example.submission1.viewmodel.FavsVM
import kotlinx.android.synthetic.main.fragment_favourite.*

class FavouriteFragment : BaseFragment<FavsVM>() {
    private lateinit var favAdapterMovie: MovieShowAdapter
    private lateinit var favAdapterTVShow: MovieShowAdapter
    private var favouriteRes: MovieRes? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onResume() {
        super.onResume()
        loadDataFavourite()
    }

    override fun onStart() {
        super.onStart()
        loadDataFavourite()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadDataFavourite()
        swiperefreshFavourite.setColorSchemeColors(
            ContextCompat.getColor(
                contextView(),
                R.color.colorAccent
            )
        )
        swiperefreshFavourite.setOnRefreshListener { loadDataFavourite() }

        favAdapterMovie = MovieShowAdapter { favData ->
            val data = Intent(contextView(), DetailMovieActivity::class.java)
            data.putExtra(Constant.INTENT_DATA, favData)
            data.putExtra(Constant.FRAGMENT_DATA, FilmType.MOVIE)
            startActivity(data)
        }
        rvlistMovieFavourite.layoutManager = LinearLayoutManager(contextView())
        rvlistMovieFavourite.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistMovieFavourite.adapter = favAdapterMovie

        favAdapterTVShow = MovieShowAdapter { favTV ->
            val TVS = Intent(contextView(), DetailMovieActivity::class.java)
            TVS.putExtra(Constant.INTENT_DATA, favTV)
            TVS.putExtra(Constant.FRAGMENT_DATA, FilmType.TVSHOW)
            startActivity(TVS)
        }
        rvlistTvShowFavourite.layoutManager = LinearLayoutManager(contextView())
        rvlistTvShowFavourite.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistTvShowFavourite.adapter = favAdapterTVShow


        savedInstanceState?.let {
            favouriteRes = it.getParcelable(Constant.INTENT_DATA)
            favouriteRes?.let { data ->
                data.results?.let { value ->
                    favAdapterMovie.setItem(value)
                    favAdapterTVShow.setItem(value)
                }
            }
        } ?: run { loadDataFavourite() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(Constant.INTENT_DATA, favouriteRes)

    }

    private fun loadDataFavourite() {
        viewmodel?.getFavouriteMovies()?.observe(viewLifecycleOwner, setShowMovie)
        viewmodel?.getFavouriteTVShow()?.observe(viewLifecycleOwner, setShowTVShow)
    }

    private val setShowMovie = Observer<MovieRes> {
        favouriteRes = it.copy()
        it.results?.let { itdata ->
            favAdapterMovie.setItem(itdata)
            onHideProgressbar()
        }
    }

    private val setShowTVShow = Observer<MovieRes> {
        favouriteRes = it.copy()
        it.results?.let { itdata ->
            favAdapterTVShow.setItem(itdata)
            onHideProgressbar()
        }
    }

    override fun onShowProgressbar() {
        swiperefreshFavourite?.isRefreshing = true
    }

    override fun onHideProgressbar() {
        swiperefreshFavourite?.isRefreshing = false
    }


    override fun initViewModel(): FavsVM {
        return ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FavsVM::class.java).apply {
            setupView(this@FavouriteFragment)
        }
    }

}
