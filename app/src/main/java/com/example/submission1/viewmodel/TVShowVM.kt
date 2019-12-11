package com.example.submission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission1.base.BaseViewModel
import com.example.submission1.model.MovieRes
import com.example.submission1.api.ApiDbMovies
import com.example.submission1.api.RxUtils
import com.example.submission1.model.SearchResponse

class TVShowVM : BaseViewModel() {
    private val movieLiveData = MutableLiveData<MovieRes>()
    private val searchTVLiveData = MutableLiveData<SearchResponse>()

    fun getTVShow() : LiveData<MovieRes> {
        view?.onShowProgressbar()
        subscriber = api<ApiDbMovies>()
                .getTV(getLocale())
                .compose(RxUtils.applyObservableAsync())
                .subscribe(onSuccess(), onFailed())

        return movieLiveData
    }

    override fun onResponseSuccess(data: Any) {
        when (data) {
            is MovieRes -> {
                movieLiveData.postValue(data)
            }
            is SearchResponse -> {
                searchTVLiveData.postValue(data)

            }
        }
    }
    fun searchTVShow(query: String): LiveData<SearchResponse> {
        view?.onShowProgressbar()
        subscriber = api<ApiDbMovies>()
            .searchTV(getLocale(), query)
            .compose(RxUtils.applyObservableAsync())
            .subscribe(onSuccess(), onFailed())
        return searchTVLiveData
    }

}