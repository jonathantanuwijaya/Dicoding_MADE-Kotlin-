package com.example.submission1.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.submission1.provider.DatabaseContract.FavoriteColumns

class MyContentProvider : ContentProvider() {

    private var favoriteHelper: FavoriteHelper? = null


    companion object {
        private const val MOVIE = 99
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(DatabaseContract.AUTHORITY, FavoriteColumns.TABLENAME, MOVIE)
        }
    }
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun onCreate(): Boolean {
        favoriteHelper = FavoriteHelper.getInstance(context)
        return false
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        favoriteHelper!!.open()
        return if (sUriMatcher.match(uri) == MOVIE) {
            favoriteHelper!!.providerQuery()
        } else {
            null
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }
}
