package com.example.submission1.provider

import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.example.submission1"
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

            fun getColumnString(cursor: Cursor, columnName:String): String? {
                return cursor.getString(cursor.getColumnIndex(columnName))
            }

            fun getColumnInt(cursor: Cursor, columnName:String): Int? {
                return cursor.getInt(cursor.getColumnIndex(columnName))
            }
            fun getColumnLong(cursor: Cursor, columnName:String): Long? {
                return cursor.getLong(cursor.getColumnIndex(columnName))
            }

        }
    }
}
