package com.example.submission1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submission1.model.VPagerTitle

class VPAdapter(private val fragments: List<VPagerTitle>, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].title
    }

    override fun getCount(): Int {
        return fragments.count()
    }
}