package com.example.submission1.api

import com.example.submission1.BuildConfig.APIKEY
import com.example.submission1.model.MovieRes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovie {

    @GET("movie?api_key=$APIKEY")
    fun getMovies(@Query("language") lang: String): Observable<MovieRes>

    @GET("tv?api_key=$APIKEY")
    fun getTV(@Query("language") lang: String): Observable<MovieRes>

    @GET("search/tv?api_key=$APIKEY")
    fun searchTV(
        @Query("language") lang: String,
        @Query("query") q: String
    ): Observable<MovieRes>

    @GET("discover/movie?api_key=$APIKEY")
    fun getRelease(
        @Query("primary_release_date.gte") startDate: String,
        @Query("primary_release_date.lte") endDate: String,
        @Query("language") lang: String
    ): Observable<MovieRes>

    @GET("search/movie?api_key=$APIKEY")
    fun searchMovies(
        @Query("language") lang: String,
        @Query("query") q: String
    ): Observable<MovieRes>
}