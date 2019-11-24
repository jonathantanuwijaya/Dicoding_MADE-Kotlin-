package com.example.submission1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieRes(
    val page: Int?,
    val results: MutableList<Movie>?,
    val total_pages: Int?,
    val total_results: Int?
):Parcelable