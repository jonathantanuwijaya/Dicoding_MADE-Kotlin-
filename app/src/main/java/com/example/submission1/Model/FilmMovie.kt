package com.example.submission1.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmMovie(
    val page: String,
    val total_results: Int,
    val total_pages: String,
    val result : List<DetailFilm>
): Parcelable