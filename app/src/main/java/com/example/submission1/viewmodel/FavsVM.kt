package com.example.submission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission1.base.BaseViewModel
import com.example.submission1.local.FavouriteFunc
import com.example.submission1.model.FavMovie
import com.example.submission1.model.FilmType
import com.example.submission1.model.Movie
import com.example.submission1.model.MovieRes

class FavsVM : BaseViewModel() {
    private val movieData = MutableLiveData<MovieRes>()
    private val tvData = MutableLiveData<MovieRes>()
    private val favsData = MutableLiveData<Boolean>()
    private lateinit var favouriteFunction: FavouriteFunc


    fun addFav(movie: Movie, type: FilmType) {
        view?.contextView()?.let {
            favouriteFunction = FavouriteFunc(it)
            favouriteFunction.insert(
                FavMovie(
                    movie.id.toString(),
                    movie.title,
                    movie.original_title,
                    movie.overview,
                    movie.backdrop_path,
                    movie.poster_path,
                    type.name
                )
            )
            favsData.postValue(true)
            when (type) {
                FilmType.MOVIE -> getFavouriteMovies()
                FilmType.TVSHOW -> getFavouriteTVShow()
            }
        }
    }

    fun setToFavourite(id: String): LiveData<Boolean> {
        view?.contextView()?.let {
            favouriteFunction = FavouriteFunc(it)
            favouriteFunction.getOne(id) { data: FavMovie? ->
                data?.let {
                    favsData.postValue(true)
                } ?: run {
                    favsData.postValue(false)
                }
            }
        }
        return favsData
    }

    fun delete(id: String) {
        view?.contextView()?.let {
            favouriteFunction = FavouriteFunc(it)
            favouriteFunction.delete(id)
            favsData.postValue(false)
        }
    }

    fun getFavouriteTVShow(): LiveData<MovieRes> {
        view?.onShowProgressbar()
        view?.contextView()?.let {
            favouriteFunction = FavouriteFunc(it)
            favouriteFunction.read(FilmType.TVSHOW) { data ->
                tvData.postValue(convertFavourite(data))
            }
        }
        return tvData
    }

    fun getFavouriteMovies(): LiveData<MovieRes> {
        view?.onShowProgressbar()
        view?.contextView()?.let {
            favouriteFunction = FavouriteFunc(it)
            favouriteFunction.read(FilmType.MOVIE) { data ->
                movieData.postValue(convertFavourite(data))
            }
        }
        return movieData
    }

    private fun convertFavourite(data: MutableList<FavMovie>): MovieRes? {
        val result = mutableListOf<Movie>()
        data.forEach {
            result.add(
                Movie(
                    it.id?.toInt(),
                    it.title,
                    it.origTitle,
                    it.overview,
                    it.backdrop_path,
                    it.poster_path,
                    null,
                    null,
                    0.0,
                    null,
                    false,
                    0.0,
                    0

                )
            )
        }
        return MovieRes(0, result, 0, 0)
    }

    override fun onResponseSuccess(data: Any) {
    }
}