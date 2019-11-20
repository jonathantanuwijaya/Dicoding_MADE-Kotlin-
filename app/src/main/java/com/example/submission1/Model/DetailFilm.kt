package com.example.submission1.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailFilm (
    val popularity: String,
    val vote_count: String,
    val video: Boolean,
    val poster_path: String,
    val id: String,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Int>,
    val title: String,
    val vote_average: String,
    val overview: String,
    val release_data: String
) : Parcelable
