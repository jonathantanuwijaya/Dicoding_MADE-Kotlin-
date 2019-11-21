package com.example.submission1.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1.R
import com.example.submission1.base.BaseRecyclerViewAdapter
import com.example.submission1.model.Movie
import com.example.submission1.utils.Constant.Companion.POSTER_MEDIUM
import com.example.submission1.utils.Constant.Companion.POSTER_URL
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_movie.*

class MovieShow(
    private val listener: (Movie) -> Unit
) : BaseRecyclerViewAdapter<MovieShow.VHolder, Movie>() {

    override fun onBindViewHolder(holder: VHolder, item: Movie, position: Int) {
        holder.binding(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.list_movie, parent, false))
    }

    class VHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun binding(movie: Movie, listener: (Movie) -> Unit) {

            tvJudulMovie.text = movie.title
            tvDeskripsiMovie.text = movie.overview

            Glide.with(containerView.context)
                    .load(POSTER_URL + POSTER_MEDIUM + movie.poster_path)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img_photo)
            containerView.setOnClickListener { listener(movie) }
        }
    }
}
