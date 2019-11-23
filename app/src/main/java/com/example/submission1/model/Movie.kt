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

@Parcelize
data class FavMovie(
    val id: String?,
    val title: String?,
    val origTitle: String?,
    val overview: String?,
    val adult: Boolean?,
    val release_date: String?,
    val vote_average: String?,
    val backdrop_path: String?,
    val poster_path: String?,
    val type: String?
) : Parcelable {
    companion object {
        const val TABLENAME = "FAVS_FILM"
        const val ID_FILM = "ID"
        const val TITLE = "FAVS_TITLE"
        const val ORIGINAL_TITLE = "FAVS_ORIGINAL_TITLE"
        const val OVERVIEW = "FAV_OVERVIEW"
        const val ADULT = "FAV_ADULT"
        const val POSTER = "FAV_POSTER"
        const val BACKDROP = "FAV_BACKDROP"
        const val RELEASE_DATE = "FAV_RELEASE_DATE"
        const val RATE = "FAVS_RATE"
        const val TYPE = "FAVS_TYPE"
    }
}
enum class FilmType {
    MOVIE, TVSHOW
}