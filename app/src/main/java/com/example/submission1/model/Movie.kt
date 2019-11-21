package com.example.submission1.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Movie(
        @SerializedName("title", alternate = ["name"])
        val title: String?,
        val adult: Boolean?,
        val backdrop_path: String?,
        val genre_ids: MutableList<Int>?,
        val id: Int?,
        val original_language: String?,
        @SerializedName("original_title", alternate = ["original_name"])
        val original_title: String?,
        val overview: String?,
        val popularity: Double?,
        val poster_path: String?,
        val release_date: String?,
        val video: Boolean?,
        val vote_average: Double?,
        val vote_count: Int?
) : Parcelable