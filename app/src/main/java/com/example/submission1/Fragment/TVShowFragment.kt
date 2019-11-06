package com.example.submission1.Fragment

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.Adapter.TVShowAdapter
import com.example.submission1.Model.FilmMovie
import com.example.submission1.Model.FilmTV
import com.example.submission1.R
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TVShowFragment : Fragment() {

    private lateinit var judulFilm: Array<String>
    private lateinit var descFilm: Array<String>
    private lateinit var foto: TypedArray
    private var movies = arrayListOf<FilmTV>()
    private var adaptermovie = TVShowAdapter(movies)



    private fun init(): ArrayList<FilmTV> {
        judulFilm = resources.getStringArray(R.array.judul_tvshow)
        descFilm = resources.getStringArray(R.array.deskripsi_tvshow)
        foto = resources.obtainTypedArray(R.array.foto_tvshow)
        val listmovies = ArrayList<FilmTV>()
        for (position in judulFilm.indices) {
            val film = FilmTV(
                judulFilm[position],
                foto.getResourceId(position, -1),
                descFilm[position]
            )
            listmovies.add(film)
        }
        return listmovies
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvlistTVShows.setHasFixedSize(true)
        movies.addAll(init())
        rvlistTVShows.layoutManager = LinearLayoutManager(context)
        rvlistTVShows.adapter = adaptermovie

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var index = 1
        if (getArguments() != null) {
            index = arguments?.getInt(ARG_SECTION_NUMBER, 0) as Int
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    companion object {
        private val ARG_SECTION_NUMBER = "section_number"


//        @JvmStatic
//        fun newInstance(index: Int): TVShowFragment {
//            val fragment = TVShowFragment()
//            val bundle = Bundle()
//            bundle.putInt(ARG_SECTION_NUMBER, index)
//            fragment.arguments = bundle
//            return fragment
//        }
    }
}
