package com.example.submission1.Pages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.submission1.BuildConfig
import com.example.submission1.R
import com.example.submission1.model.Movie
import com.example.submission1.utils.Constant
import com.example.submission1.viewmodel.MovieVM
import kotlinx.android.synthetic.main.activity_detail_movie.*


class DetailMovieActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        initViewModel()
        initViewConfigure()
    }

    override fun onDestroy() {
        super.onDestroy()
        initViewModel().onDestroy()
    }

    @SuppressLint("SetTextI18n")
    private fun initViewConfigure() {

        val movie = intent.getParcelableExtra<Movie>(Constant.INTENT_DATA)

        tvJudulMovieDetail.text = movie.title
        tvDeskripsiMovieDetail.text = movie.overview

        Glide.with(this)
            .load(BuildConfig.BASE_URL_POSTER + Constant.POSTER_LARGE + movie.backdrop_path)
            .placeholder(R.mipmap.ic_launcher)
            .into(ivMovie)

        Glide.with(this)
            .load(BuildConfig.BASE_URL_POSTER + Constant.POSTER_MEDIUM + movie.poster_path)
            .placeholder(R.mipmap.ic_launcher)
            .into(ivMovie)
    }

    fun initViewModel(): MovieVM =
        ViewModelProviders.of(this).get(MovieVM::class.java)
}
