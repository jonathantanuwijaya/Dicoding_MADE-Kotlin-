package com.example.submission1.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.submission1.provider.DatabaseContract.FavoriteColumns

class DBHelper internal constructor(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // Creating Tables
    override fun onCreate(db: SQLiteDatabase) { // create notes table
        db.execSQL("CREATE TABLE " + FavoriteColumns.TABLENAME + "("
                + FavoriteColumns.ID_FILM + " TEXT PRIMARY KEY,"
                + FavoriteColumns.TITLE + " TEXT,"
                + FavoriteColumns.ORIGINAL_TITLE + " TEXT,"
                + FavoriteColumns.OVERVIEW + " TEXT,"
                + FavoriteColumns.POSTER + " TEXT,"
                + FavoriteColumns.BACKDROP + " TEXT,"
                + FavoriteColumns.TYPE + " TEXT)"
        )


    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteColumns.TABLENAME)

        onCreate(db)
    }

    companion object {

        private const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "movie_db"
    }
}