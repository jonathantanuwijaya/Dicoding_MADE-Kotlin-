package com.example.submission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission1.base.BaseViewModel
import com.example.submission1.model.MovieRes
import com.example.submission1.api.ApiDbMovies
import com.example.submission1.api.RxUtils
import com.example.submission1.model.SearchResponse

class MovieVM : BaseViewModel() {
    private val movieLiveData = MutableLiveData<MovieRes>()
    private val searchMovieLiveData = MutableLiveData<SearchResponse>()

    fun getMovie(): LiveData<MovieRes> {
        view?.onShowProgressbar()
        subscriber = api<ApiDbMovies>()
            .getMovies(getLocale())
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())

        return movieLiveData
    }

    fun searchMovie(query: String): LiveData<SearchResponse> {
        view?.onShowProgressbar()
        subscriber = api<ApiDbMovies>()
            .searchMovies(getLocale(), query)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
        return searchMovieLiveData
    }

    override fun onResponseSuccess(data: Any) {
        when (data) {
            is MovieRes -> {
                movieLiveData.postValue(data)
            }
            is SearchResponse ->{
                searchMovieLiveData.postValue(data)
            }
        }
    }
}