package com.example.submission1.Fragment

import android.content.Context
import android.content.res.TypedArray
import android.net.Uri
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


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MoviesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class MoviesFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var judulFilm: Array<String>
    private lateinit var descFilm: Array<String>
    private lateinit var foto: TypedArray
    private var movies = arrayListOf<FilmMovie>()
    private var adaptermovie = MovieAdapter(movies)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//    }

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
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        listener = null
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        private val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int): MoviesFragment {
            val fragment = MoviesFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }
}
