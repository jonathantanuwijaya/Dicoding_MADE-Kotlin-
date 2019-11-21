package com.example.submission1.model

import com.example.submission1.model.Movie

data class MovieRes(
    val page: Int?,
    val results: MutableList<Movie>?,
    val total_pages: Int?,
    val total_results: Int?
)