package com.example.submission1.local

import android.content.Context
import com.example.submission1.model.FavMovie
import com.example.submission1.model.FilmType
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

class FavouriteFunc(val context: Context) {
    fun insert(favourite: FavMovie) {
        context.database.use {
            insert(
                FavMovie.TABLENAME,
                FavMovie.ID_FILM to favourite.id,
                FavMovie.TITLE to favourite.title,
                FavMovie.ORIGINAL_TITLE to favourite.origTitle,
                FavMovie.OVERVIEW to favourite.overview,
                FavMovie.BACKDROP to favourite.backdrop_path,
                FavMovie.POSTER to favourite.poster_path,
                FavMovie.TYPE to favourite.type
            )
        }
    }

    fun delete(id: String) {
        context.database.use {
            delete(
                FavMovie.TABLENAME,
                FavMovie.ID_FILM + "={id}", "id" to id
            )
        }
    }

    fun read(type: FilmType, listener: (MutableList<FavMovie>) -> Unit) {
        context.database.use {
            val data = mutableListOf<FavMovie>()
            val result = select(FavMovie.TABLENAME)
                .whereArgs(FavMovie.TYPE + "={type}", "type" to type.name)
            data.addAll(result.parseList(classParser()))
            listener(data)
        }
    }

    fun getOne(id: String, listener: (FavMovie?) -> Unit) {
        context.database.use {
            val result = select(FavMovie.TABLENAME)
                .whereArgs(
                    FavMovie.ID_FILM + "={id}",
                    "id" to id
                )
            val favourite = result.parseList(classParser<FavMovie>())
            if (favourite.isNullOrEmpty()) {
                listener(null)
            } else {
                listener(favourite[0])
            }
        }
    }

}