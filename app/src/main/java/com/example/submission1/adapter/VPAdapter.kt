package com.example.submission1.adapter

import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submission1.R
import com.example.submission1.fragments.FavouriteFragment
import com.example.submission1.model.VPagerTitle

class VPAdapter(private val fragments: List<VPagerTitle>, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private  var menuitem: Menu ? = null

    override fun getItem(position: Int): Fragment {
//        menu?.findItem(R.id.menu_search)?.isVisible = this.page != FavouriteFragment()
//         var menuitem =Menu
        when (fragments[position].fragment) {
            is FavouriteFragment
            -> menuitem?.findItem(R.id.menu_search)?.isVisible = false
        }
        return fragments[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].title
    }

    override fun getCount(): Int {
        return fragments.count()
    }
}