package com.example.submission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission1.base.BaseViewModel
import com.example.submission1.model.MovieRes
import com.example.submission1.Api.ApiMovie
import com.example.submission1.utils.RxUtils

class MovieVM : BaseViewModel() {
    private val movieLiveData = MutableLiveData<MovieRes>()

    fun getMovie() : LiveData<MovieRes> {
        view?.onShowProgressbar()
        subscriber = api<ApiMovie>()
                .getMovies(getLocale())
                .compose(RxUtils.applyObservableAsync())
                .subscribe(onSuccess(), onFailed())

        return movieLiveData
    }

    override fun onResponseSuccess(data: Any) {
        when (data) {
            is MovieRes -> {
                movieLiveData.postValue(data)
            }
        }
    }
}