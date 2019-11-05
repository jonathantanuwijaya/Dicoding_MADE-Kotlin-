package com.example.submission1.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1.Pages.DetailActivity
import com.example.submission1.Model.FilmMovie
import com.example.submission1.R
import kotlinx.android.synthetic.main.list_movie.view.*

class MovieAdapter(private val listmovies: ArrayList<FilmMovie>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.list_movie, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listmovies[position])
    }

    override fun getItemCount(): Int = listmovies.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(movies: FilmMovie) {

            val parcelablemovie = FilmMovie(
                movies.judul,
                movies.foto,
                movies.deskripsi
            )

            with(itemView) {
                Glide.with(itemView.context)
                    .load(movies.foto)
                    .apply(RequestOptions().override(100, 150))
                    .into(img_photo)

                tvJudulMovie.text = movies.judul
                tvDeskripsiMovie.text = movies.deskripsi


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, parcelablemovie)
                    itemView.context!!.startActivity(intent)
                }
            }


        }
    }
}


