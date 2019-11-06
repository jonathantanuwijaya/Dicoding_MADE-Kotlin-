package com.example.submission1.Pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission1.Model.FilmMovie
import com.example.submission1.Model.FilmTV
import com.example.submission1.R
import kotlinx.android.synthetic.main.activity_detail_tvshow.*

class DetailTVShow : AppCompatActivity() {
    companion object{
        const val EXTRA_SHOW = "extra_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)

        val tvshowparsed = intent.getParcelableExtra(EXTRA_SHOW) as FilmTV
        tvDeskripsiTVshow.text = tvshowparsed.deskripsi
        tvJudulTVShow.text = tvshowparsed.judul
        ivTVShow.setImageResource(tvshowparsed.foto)

    }
}
