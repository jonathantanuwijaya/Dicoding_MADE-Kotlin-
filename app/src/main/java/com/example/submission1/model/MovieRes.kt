package com.example.submission1.model


data class MovieRes(
    val page: Int?,
    val results: MutableList<Movie>?,
    val total_pages: Int?,
    val total_results: Int?
)