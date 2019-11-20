package com.example.submission1.Fragment


import android.content.res.TypedArray

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.Adapter.MovieAdapter
import com.example.submission1.Model.FilmMovie
import com.example.submission1.R
import kotlinx.android.synthetic.main.fragment_movies.*



class MoviesFragment : Fragment() {

    private lateinit var judulFilm: Array<String>
    private lateinit var descFilm: Array<String>
    private lateinit var foto: TypedArray
    private var movies = arrayListOf<FilmMovie>()
    private var adaptermovie = MovieAdapter(movies)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvlistMovie.setHasFixedSize(true)

        movies.addAll(init())
        rvlistMovie.layoutManager = LinearLayoutManager(context)
        rvlistMovie.adapter = adaptermovie


    }
    private fun init(): ArrayList<FilmMovie> {
        judulFilm = resources.getStringArray(R.array.judul_movie)
        descFilm = resources.getStringArray(R.array.deskripsi_movie)
        foto = resources.obtainTypedArray(R.array.foto_movie)
        val listmovies = ArrayList<FilmMovie>()
        for (position in judulFilm.indices) {
            val film = FilmMovie(
                judulFilm[position],
                foto.getResourceId(position, -1),
                descFilm[position]
            )
            listmovies.add(film)
        }
        return listmovies
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var index = 0
        if (getArguments() != null) {
            index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }


    companion object {
        private val ARG_SECTION_NUMBER = "section_number"

    }
}
