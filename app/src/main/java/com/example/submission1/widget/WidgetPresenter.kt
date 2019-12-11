package com.example.submission1.widget

import android.content.Context
import com.example.submission1.local.FavouriteFunc
import com.example.submission1.model.FavMovie
import com.example.submission1.model.FilmType

class WidgetPresenter(val context: Context) {
    private val favoriteService = FavouriteFunc(context)

    fun getWidgetContent() : MutableList<FavMovie?> {
        val datas = mutableListOf<FavMovie?>()

        favoriteService.read(FilmType.MOVIE) {
            datas.addAll(it)
        }

        favoriteService.read(FilmType.TVSHOW) {
            datas.addAll(it)
        }

        return datas
    }
}