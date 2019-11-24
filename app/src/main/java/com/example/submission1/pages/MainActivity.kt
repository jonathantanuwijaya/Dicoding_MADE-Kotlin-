package com.example.submission1.pages

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.submission1.adapter.VPAdapter
import com.example.submission1.R
import com.example.submission1.base.BaseActivity
import com.example.submission1.fragments.FavouriteFragment
import com.example.submission1.fragments.MovieFragment
import com.example.submission1.fragments.TVShowFm
import com.example.submission1.model.VPagerTitle
import com.example.submission1.utils.Constant.Companion.FRAGMENT_DATA
import com.example.submission1.viewmodel.MovieVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MovieVM>() {
    private var page: Fragment = FavouriteFragment()
    override fun initViewModel(): MovieVM {
        return ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MovieVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frags = ArrayList<VPagerTitle>()
        frags.add(
            VPagerTitle(
                getString(R.string.tab_text_1),
                MovieFragment()
            )
        )
        frags.add(
            VPagerTitle(
                getString(R.string.tab_text_2),
                TVShowFm()
            )
        )
        frags.add(
            VPagerTitle(
                getString(R.string.tab_text_3),
                FavouriteFragment()
            )
        )
        view_pager.adapter = VPAdapter(frags, supportFragmentManager)
        view_pager.overScrollMode = View.OVER_SCROLL_NEVER
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f




    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        supportFragmentManager.putFragment(outState, FRAGMENT_DATA, page)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}