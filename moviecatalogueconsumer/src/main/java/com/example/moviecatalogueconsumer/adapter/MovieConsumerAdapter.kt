package com.example.moviecatalogueconsumer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogueconsumer.R
import com.example.moviecatalogueconsumer.base.BaseRecyclerViewAdapter
import com.example.moviecatalogueconsumer.model.FavMovie
import com.example.moviecatalogueconsumer.utility.Constans.Companion.BASE_POSTER_URL
import com.example.moviecatalogueconsumer.utility.Constans.Companion.POSTER_MEDIUM
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_movie_consumer.*

class MovieConsumerAdapter(
    private val listener: (FavMovie) -> Unit
) : BaseRecyclerViewAdapter<MovieConsumerAdapter.ViewHolder, FavMovie>() {

    override fun onBindViewHolder(holder: ViewHolder, item: FavMovie, position: Int) {
        holder.bind(item, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.list_movie_consumer, parent, false))
    }

    class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bind(movie: FavMovie, listener: (FavMovie) -> Unit) {
            tvJudulMovieC.text = movie.title
            tvDeskripsiC.text = movie.overview

            Glide.with(containerView.context)
                    .load(BASE_POSTER_URL + POSTER_MEDIUM + movie.poster_path)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img_photoC)
            containerView.setOnClickListener { listener(movie) }
        }
    }
}
