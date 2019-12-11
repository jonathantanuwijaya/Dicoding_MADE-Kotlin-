package com.example.submission1.local

import android.content.Context
import com.example.submission1.model.FavMovie
import com.example.submission1.model.FilmType
import com.example.submission1.provider.FavoriteHelper

class FavouriteFunc(val context: Context) {
    private var favoriteHelper = FavoriteHelper.getInstance(context)
    fun add(favorite: FavMovie) {
        favoriteHelper?.open()
        favoriteHelper?.insert(favorite)
        favoriteHelper?.close()
    }

    fun delete(id: String) {
        favoriteHelper?.open()
        favoriteHelper?.delete(id)
        favoriteHelper?.close()
    }

    fun read(type: FilmType, listener: (MutableList<FavMovie?>) -> Unit) {
        favoriteHelper?.open()
        listener(favoriteHelper!!.query(type.name))
        favoriteHelper?.close()
    }

    fun readOne(id: String, listener: (FavMovie?) -> Unit){
        favoriteHelper?.open()
        listener(favoriteHelper?.queryById(id))
        favoriteHelper?.close()
    }

}