package com.example.submission1.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import com.example.submission1.provider.DatabaseContract
import com.example.submission1.utils.Constant
import com.example.submission1.viewmodel.TVShowVM
import kotlinx.android.synthetic.main.main_fragment.*


class TVShowFm : BaseFragment<TVShowVM>() {
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

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swiperefresh.setColorSchemeColors(
            ContextCompat.getColor(
                contextView(),
                R.color.colorAccent
            )
        )
        swiperefresh.setOnRefreshListener {
            viewmodel?.getTVShow()?.observe(viewLifecycleOwner, setTVShow)
        }

        showAdapter = MovieShowAdapter {
            val intent = Intent(contextView(), DetailMovieActivity::class.java)
            val uri = Uri.parse(DatabaseContract.FavoriteColumns.CONTENT_URI.toString() + "/"+it.id)
            intent.data = uri
            intent.putExtra(Constant.INTENT_DATA, it)
            intent.putExtra(Constant.FRAGMENT_DATA, FilmType.TVSHOW)
            startActivity(intent)
        }

        rvlistMovie.layoutManager = LinearLayoutManager(contextView())
        rvlistMovie.overScrollMode = View.OVER_SCROLL_NEVER
        rvlistMovie.adapter = showAdapter
        viewmodel?.getTVShow()?.observe(viewLifecycleOwner, setTVShow)
    }

    private val setSearch = Observer<SearchResponse> {
        movieResponse = MovieRes(it.page, it.results, it.total_pages, it.total_results)
        if (it.results.isNullOrEmpty() ){
            Toast.makeText(requireContext(),"Data tidak ditemukan", Toast.LENGTH_SHORT).show()

        }
        it.results?.let { data -> showAdapter.setItem(data) }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        mSearch = menu.findItem(R.id.menu_search)
        searchView = mSearch.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { search(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    if (newText != itemQuery)
                        search(newText)
                }
                return true
            }

        })

        super.onPrepareOptionsMenu(menu)
    }


    private fun search(query: String?) {
        itemQuery = query.toString()
        if (query.isNullOrEmpty()) {
            viewmodel?.getTVShow()?.observe(viewLifecycleOwner, setTVShow)
        } else {
            viewmodel?.searchTVShow(query)?.observe(viewLifecycleOwner, setSearch)
        }
    }

    private val setTVShow = Observer<MovieRes> {
        movieResponse =it.copy()
        it.results?.let { it1 -> showAdapter.setItem(it1) }
    }

    override fun initViewModel(): TVShowVM {
        return ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TVShowVM::class.java).apply {
            setupView(this@TVShowFm)
        }

    }

    override fun onShowProgressbar() {
        swiperefresh.isRefreshing = true
    }

    override fun onHideProgressbar() {
        swiperefresh.isRefreshing = false
    }

}