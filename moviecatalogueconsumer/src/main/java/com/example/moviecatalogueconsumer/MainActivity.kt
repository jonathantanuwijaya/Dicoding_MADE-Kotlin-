package com.example.moviecatalogueconsumer

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogueconsumer.adapter.MovieConsumerAdapter
import com.example.moviecatalogueconsumer.model.FavMovie
import com.example.moviecatalogueconsumer.provider.DatabaseContract
import com.example.moviecatalogueconsumer.utility.Constans.Companion.INTENT_DATA
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MovieConsumerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swiperefresh.setOnRefreshListener { loadData() }

        adapter = MovieConsumerAdapter {
            val navs = Intent(this, DetailConsumer::class.java)
            navs.putExtra(INTENT_DATA, it)
            startActivityForResult(navs, 0)
        }

        list_item.layoutManager = LinearLayoutManager(this)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
        loadData()

    }

    @SuppressLint("Recycle")
    private fun loadData() {
        val items = mutableListOf<FavMovie>()
        val cursor = contentResolver.query(
                DatabaseContract.FavoriteColumns.CONTENT_URI,
                null,
                null,
                null,
                null)

        cursor?.let {
            it.moveToFirst()
            for (i in 0 until it.count) {
                getMovieFromCursor(it)?.let { it1 -> items.add(it1) }
                it.moveToNext()
            }
        }

        adapter.setItem(items)
        swiperefresh.isRefreshing = false
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

}
