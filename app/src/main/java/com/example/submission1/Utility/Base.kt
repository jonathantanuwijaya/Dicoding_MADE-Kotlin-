package com.example.submission1.Utility

class Base {

    val BaseURL_Movie :String = "https://api.themoviedb.org/3/discover/movie?api_key=191221b09fedeae5ffb6cf9443f7b97a&language=en-US"
    val BaseURL_FilmTV : String
        get() = "https://api.themoviedb.org/3/discover/tv?api_key=${this.APIKEY}&language=en-US"
    val BaseURL_Foto : String = "https://image.tmdb.org/t/p/original/{POSTER FILENAME}"
    val APIKEY:String ="191221b09fedeae5ffb6cf9443f7b97a"

}