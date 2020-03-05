package com.example.submission1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.adapter.MovieShowAdapter
import com.example.submission1.base.BaseFragment
import com.example.submission1.model.FilmType
import com.example.submission1.model.MovieRes
import com.example.submission1.model.SearchResponse
import com.example.submission1.pages.DetailMovieActivity
import com.example.submission1.utils.Constant
import com.example.submission1.viewmodel.MovieVM
import kotlinx.android.synthetic.main.main_fragment.*

class MovieFragment : BaseFragment<MovieVM>() {

    private lateinit var showAdapter: MovieShowAdapter
    private var itemQuery = ""
    private lateinit var mSearch: MenuItem
    private lateinit var searchView: SearchView
    private var movieResponse: MovieRes? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swiperefresh.setColorSchemeColors(
            ContextCompat.getColor(
                contextView(),
                R.color.colorAccent
            )
        )
        swiperefresh.setOnRefreshListener {
            viewmodel?.getMovie()?.observe(viewLifecycleOwner, setMovies)
        }

        showAdapter = MovieShowAdapter { movie ->
            val intent = Intent(contextView(), DetailMovieActivity::class.java)
//            val uri = Uri.parse(CONTENT_URI.toString() + "/"+movie.id)
//            intent.setData(uri)
            intent.putExtra(Constant.INTENT_DATA, movie)
            intent.putExtra(Constant.FRAGMENT_DATA, FilmType.MOVIE)
            startActivity(intent)
        }

        viewmodel?.getMovie()?.observe(viewLifecycleOwner, setMovies)
        rvlistMovie.layoutManager = LinearLayoutManager(contextView())
        rvlistMovie.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistMovie.adapter = showAdapter
    }



    private fun search(query: String?) {
        itemQuery = query.toString()
        if (query.isNullOrEmpty()) {
            viewmodel?.getMovie()?.observe(viewLifecycleOwner, setMovies)
        } else {
            viewmodel?.searchMovie(query)?.observe(viewLifecycleOwner, setSearch)
        }
    }

    private val setMovies = Observer<MovieRes> {
        movieResponse = it.copy()
        it.results?.let { it2 -> showAdapter.setItem(it2) }
    }

    private val setSearch = Observer<SearchResponse> {
        movieResponse = MovieRes(it.page, it.results, it.total_pages, it.total_results)
        if (it.results.isNullOrEmpty() ){
            Toast.makeText(requireContext(),"Data tidak ditemukan",Toast.LENGTH_SHORT).show()

        }


        it.results?.let { data -> showAdapter.setItem(data) }
    }

    override fun initViewModel(): MovieVM {
        return ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieVM::class.java).apply {
            setupView(this@MovieFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        mSearch = menu.findItem(R.id.menu_search)
        searchView = mSearch.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { search(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()){
                    if (newText != itemQuery)
                        search(newText)
                }
                return true
            }

        })

        super.onPrepareOptionsMenu(menu)
    }

    override fun onShowProgressbar() {
        swiperefresh?.isRefreshing = true
    }

    override fun onHideProgressbar() {
        swiperefresh?.isRefreshing = false
    }


}
