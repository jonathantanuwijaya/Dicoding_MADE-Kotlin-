package com.example.submission1.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1.Model.FilmTV
import com.example.submission1.Pages.DetailTVShow
import com.example.submission1.R
import kotlinx.android.synthetic.main.list_movie.view.*

class TVShowAdapter(private val listTVShows: ArrayList<FilmTV>) :
    RecyclerView.Adapter<TVShowAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = listTVShows.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listTVShows[position])
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvshow: FilmTV) {

            val parcelabletvshows = FilmTV(
                tvshow.judul,
                tvshow.foto,
                tvshow.deskripsi
            )

            with(itemView) {
                Glide.with(itemView.context)
                    .load(tvshow.foto)
                    .apply(RequestOptions().override(100, 150))
                    .into(img_photo)

                tvJudulMovie.text = tvshow.judul
                tvDeskripsiMovie.text = tvshow.deskripsi


                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTVShow::class.java)
                    intent.putExtra(DetailTVShow.EXTRA_SHOW, parcelabletvshows)
                    itemView.context!!.startActivity(intent)
                }
            }


        }
    }

}