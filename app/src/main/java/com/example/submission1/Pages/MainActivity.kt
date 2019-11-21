package com.example.submission1.Pages

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.submission1.Adapter.VPAdapter
import com.example.submission1.R
import com.example.submission1.base.BaseActivity
import com.example.submission1.model.VPagerTitle
import com.example.submission1.viewmodel.MovieVM
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MovieVM>() {

    override fun initViewModel(): MovieVM {
        return MovieVM()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frags = ArrayList<VPagerTitle>()
        frags.add(VPagerTitle(getString(R.string.tab_text_1), MovieFragment()))
        frags.add(VPagerTitle(getString(R.string.tab_text_2), TVShowFm()))

        view_pager.adapter = VPAdapter(frags, supportFragmentManager)
        view_pager.overScrollMode = View.OVER_SCROLL_NEVER
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f


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