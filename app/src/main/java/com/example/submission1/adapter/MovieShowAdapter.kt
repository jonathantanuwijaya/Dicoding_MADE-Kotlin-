package com.example.submission1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1.BuildConfig.BASE_URL_POSTER
import com.example.submission1.R
import com.example.submission1.base.BaseRecyclerViewAdapter
import com.example.submission1.model.Movie
import com.example.submission1.utils.Constant.Companion.POSTER_MEDIUM
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_movie.*

class MovieShowAdapter(
    private val listener: (Movie) -> Unit
) : BaseRecyclerViewAdapter<MovieShowAdapter.ViewHolder, Movie>() {

    override fun onBindViewHolder(holder: ViewHolder, item: Movie, position: Int) {
        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.list_movie, parent, false))
    }

    class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie, listener: (Movie) -> Unit) {

            tvJudulMovie.text = movie.title
            tvDeskripsiMovie.text = movie.overview

            Glide.with(containerView.context)
                    .load(BASE_URL_POSTER + POSTER_MEDIUM + movie.poster_path)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img_photo)
            containerView.setOnClickListener { listener(movie) }
        }
    }
}
