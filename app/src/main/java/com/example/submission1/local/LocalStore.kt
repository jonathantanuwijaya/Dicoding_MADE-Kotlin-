package com.example.submission1.local

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.submission1.model.FavMovie
import com.example.submission1.utils.Constant.Companion.DB_FAVOURITE
import org.jetbrains.anko.db.*


val Context.database: LocalStore
    get() = LocalStore.getInstance(this)

class LocalStore(context: Context) : ManagedSQLiteOpenHelper(
    context, DB_FAVOURITE, null, 2
) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: LocalStore? = null

        @Synchronized
        fun getInstance(context: Context): LocalStore = (instance ?: run {
            instance = LocalStore(context)
            instance
        }) as LocalStore
    }

    override fun onCreate(db: SQLiteDatabase?) {
        FavMovie.apply {
            db?.createTable(
                TABLENAME, true,
                ID_FILM to TEXT + PRIMARY_KEY,
                TITLE to TEXT,
                ORIGINAL_TITLE to TEXT,
                OVERVIEW to TEXT,
                BACKDROP to TEXT,
                POSTER to TEXT,
                TYPE to TEXT
            )

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavMovie.TABLENAME, true)
    }

}