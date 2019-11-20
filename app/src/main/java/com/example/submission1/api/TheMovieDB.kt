package com.example.submission1.api

import com.example.submission1.BuildConfig

object TheMovieDB {

    fun getMovie(movie: String): String {
        return BuildConfig.BASE_URL + BuildConfig.MOVIE + BuildConfig.APIKEY + BuildConfig.QUERY_LANGUANGE
    }

    fun getTVShow(tv: String): String {
        return BuildConfig.BASE_URL + BuildConfig.TV + BuildConfig.APIKEY + BuildConfig.QUERY_LANGUANGE
    }

    fun getPoster(poster: String): String {
        return BuildConfig.BASE_URL_POSTER + "/original" + poster
    }

}