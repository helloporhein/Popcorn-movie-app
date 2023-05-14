package com.example.popcorn.categories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    //var tabCount: Int

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = MoviesFragment()
            }
            1 -> {
                fragment = ActionFragment()
            }
            2 -> {
                fragment = AdventureFragment()
            }
            3 -> {
                fragment = AnimationFragment()
            }
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> {
                title = "All Genres";
            }
            1 -> {
                title = "Action";
            }
            2 -> {
                title = "Adventure";
            }
            3 -> {
                title = "Animation"
            }
        }
        return title;

    }
}