package com.example.submission1.local

import android.content.Context
import com.example.submission1.model.FavMovie
import com.example.submission1.model.FilmType

class FavouriteFunc(val context: Context) {
    fun insert(favourite: FavMovie) {
        context.database.use { }
    }

    fun delete(id: String) {
        context.database.use { }
    }

    fun read(type: FilmType, listener: (MutableList<FavMovie>) -> Unit) {
        context.database.use { }
    }

    fun getOne(id: String, listener: (FavMovie?) -> Unit) {
        context.database.use { }
    }

}