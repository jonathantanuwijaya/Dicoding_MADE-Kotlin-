package com.example.submission1.provider

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.submission1.model.FavMovie
import java.util.*

class FavoriteHelper private constructor(context: Context?) {
    private val dataBaseHelper: DBHelper = DBHelper(context)
    private var database: SQLiteDatabase? = null
    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()
        if (database!!.isOpen) database!!.close()
    }

    fun query(type: String): MutableList<FavMovie?> {
        val arrayList = ArrayList<FavMovie?>()
        val cursor = providerQuery(type)
        cursor.moveToFirst()
        if (cursor.count > 0) {
            do {
                arrayList.add(getMovieFromCursor(cursor))
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun queryById(id: String): FavMovie? {
        val cursor = providerQueryById(id)
        cursor.moveToFirst()
        return getMovieFromCursor(cursor)
    }

    fun insert(movie: FavMovie): Long {
        val initialValues = ContentValues()
        initialValues.put(DatabaseContract.FavoriteColumns.ID_FILM, movie.id)
        initialValues.put(DatabaseContract.FavoriteColumns.TITLE, movie.title)
        initialValues.put(DatabaseContract.FavoriteColumns.ORIGINAL_TITLE, movie.origTitle)
        initialValues.put(DatabaseContract.FavoriteColumns.OVERVIEW, movie.overview)
        initialValues.put(DatabaseContract.FavoriteColumns.POSTER, movie.poster_path)
        initialValues.put(DatabaseContract.FavoriteColumns.BACKDROP, movie.backdrop_path)
        initialValues.put(DatabaseContract.FavoriteColumns.TYPE, movie.type)
        return database!!.insert(DATABASE_TABLE, null, initialValues)
    }

    fun delete(id: String): Int {
        return database!!.delete(
                DatabaseContract.FavoriteColumns.TABLENAME,
                DatabaseContract.FavoriteColumns.ID_FILM + " = '" + id + "'",
                null)
    }

    private fun providerQuery(type: String): Cursor {
        return database!!.query(DATABASE_TABLE
                , null
                , DatabaseContract.FavoriteColumns.TYPE + " = ?"
                , arrayOf(type), null
                , null, DatabaseContract.FavoriteColumns.ID_FILM + " DESC"
                , null)
    }

    fun providerQuery(): Cursor {
        return database!!.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, DatabaseContract.FavoriteColumns.ID_FILM + " DESC"
                , null)
    }

    private fun providerQueryById(id: String): Cursor {
        return database!!.query(DATABASE_TABLE
                , null
                , DatabaseContract.FavoriteColumns.ID_FILM + " = ?"
                , arrayOf(id), null
                , null
                , null
                , null)
    }

    private fun getMovieFromCursor(cursor: Cursor): FavMovie? {
        return if (cursor.count > 0) {
            FavMovie(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.ID_FILM)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.ORIGINAL_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.OVERVIEW)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.BACKDROP)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.POSTER)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.TYPE))
            )
        } else {
            null
        }
    }

    companion object {
        private const val DATABASE_TABLE = DatabaseContract.FavoriteColumns.TABLENAME
        private var INSTANCE: FavoriteHelper? = null
        fun getInstance(context: Context?): FavoriteHelper? {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FavoriteHelper(context)
                    }
                }
            }
            return INSTANCE
        }
    }

}