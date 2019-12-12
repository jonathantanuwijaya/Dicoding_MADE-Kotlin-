package com.example.moviecatalogueconsumer.provider

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.example.moviecatalogueconsumer"
    private const val SCHEME = "content"

    class FavoriteColumns : BaseColumns {
        companion object {
                const val TABLENAME = "FAVS_FILM"
                const val ID_FILM = "ID"
                const val TITLE = "FAVS_TITLE"
                const val ORIGINAL_TITLE = "FAVS_ORIGINAL_TITLE"
                const val OVERVIEW = "FAV_OVERVIEW"
                const val POSTER = "FAV_POSTER"
                const val BACKDROP = "FAV_BACKDROP"
                const val TYPE = "FAVS_TYPE"


            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                    .authority(AUTHORITY)
                    .appendPath(TABLENAME)
                    .build()

        }
    }
}
