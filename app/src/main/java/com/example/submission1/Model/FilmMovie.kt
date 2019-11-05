package com.example.submission1.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmMovie(
    val judul: String,
    val foto: Int,
    val deskripsi: String
) : Parcelable