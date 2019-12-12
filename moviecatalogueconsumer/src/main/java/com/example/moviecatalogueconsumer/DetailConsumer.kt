package com.example.moviecatalogueconsumer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviecatalogueconsumer.model.FavMovie
import com.example.moviecatalogueconsumer.utility.Constans.Companion.BASE_POSTER_URL
import com.example.moviecatalogueconsumer.utility.Constans.Companion.INTENT_DATA
import com.example.moviecatalogueconsumer.utility.Constans.Companion.POSTER_MEDIUM
import kotlinx.android.synthetic.main.activity_detail_consumer.*

class DetailConsumer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_consumer)
        initViewConfigure()
    }

    @SuppressLint("SetTextI18n")
    private fun initViewConfigure() {
        val movie = intent?.getParcelableExtra<FavMovie>(INTENT_DATA)

        tvJudulMovieDetail.text = movie?.title
        tvDeskripsiMovieDetail.text = movie?.overview

        Glide.with(this)
                .load( BASE_POSTER_URL + movie?.backdrop_path)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivMovie)

        Glide.with(this)
                .load(BASE_POSTER_URL + POSTER_MEDIUM + movie?.poster_path)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivMovie)
    }

}
