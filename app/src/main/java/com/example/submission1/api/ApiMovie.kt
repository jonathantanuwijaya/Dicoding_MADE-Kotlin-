package com.example.submission1.api

import com.example.submission1.BuildConfig.APIKEY
import com.example.submission1.model.MovieRes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovie {

    @GET("movie?api_key=$APIKEY")
    fun getMovies(@Query("language") lang: String) : Observable<MovieRes>

    @GET("tv?api_key=$APIKEY")
    fun getTV(@Query("language") lang: String) : Observable<MovieRes>


}