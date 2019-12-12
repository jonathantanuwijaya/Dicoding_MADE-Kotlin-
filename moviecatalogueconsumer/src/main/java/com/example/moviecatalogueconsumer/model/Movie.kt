package com.example.moviecatalogueconsumer.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FavMovie(
    val id: String?,
    val title: String?,
    val origTitle: String?,
    val overview: String?,
    val backdrop_path: String?,
    val poster_path: String?,
    val type: String?
) : Parcelable



