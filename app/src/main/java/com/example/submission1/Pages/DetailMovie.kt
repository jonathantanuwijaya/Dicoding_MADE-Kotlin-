package com.example.submission1.Pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission1.Model.FilmMovie
import com.example.submission1.R
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovie : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movieparsed = intent.getParcelableExtra(EXTRA_MOVIE) as FilmMovie
        tvDeskripsiMovieDetail.text = movieparsed.deskripsi
        tvJudulMovieDetail.text = movieparsed.judul
        ivMovie.setImageResource(movieparsed.foto)

    }
}
