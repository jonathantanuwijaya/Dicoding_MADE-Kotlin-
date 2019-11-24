package com.example.submission1.pages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.submission1.BuildConfig
import com.example.submission1.R
import com.example.submission1.base.BaseActivity
import com.example.submission1.model.FilmType
import com.example.submission1.model.Movie
import com.example.submission1.utils.Constant
import com.example.submission1.viewmodel.FavsVM
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : BaseActivity<FavsVM>() {
    private lateinit var type: FilmType
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
        type = intent.getSerializableExtra(Constant.FRAGMENT_DATA) as FilmType
        tvJudulMovieDetail.text = movie.title
        tvDeskripsiMovieDetail.text = movie.overview

        iv_btnFav.setOnClickListener {
            if (it.tag as Boolean) {
                viewmodel.delete(movie?.id.toString())
            } else {
                viewmodel.addFav(movie, type)
            }
            viewmodel.setToFavourite(movie.id.toString()).observe(this, setFavButton)
        }

        viewmodel.setToFavourite(movie.id.toString()).observe(this, setFavButton)

        Glide.with(this)
            .load(BuildConfig.BASE_URL_POSTER + Constant.POSTER_LARGE + movie.backdrop_path)
            .placeholder(R.mipmap.ic_launcher)
            .into(ivMovie)

        Glide.with(this)
            .load(BuildConfig.BASE_URL_POSTER + Constant.POSTER_MEDIUM + movie.poster_path)
            .placeholder(R.mipmap.ic_launcher)
            .into(ivMovie)
    }

    private val setFavButton = Observer<Boolean> {
        iv_btnFav.tag = it
        if (it) {
            iv_btnFav.setImageResource(R.drawable.ic_favorite_black_24dp)
        } else {
            iv_btnFav.setImageResource(R.drawable.ic_favourite_inactive)
        }
    }

    override fun initViewModel(): FavsVM {
        val vm = ViewModelProviders.of(this).get(FavsVM::class.java)
        vm.setupView(this)
        return vm
    }


}

