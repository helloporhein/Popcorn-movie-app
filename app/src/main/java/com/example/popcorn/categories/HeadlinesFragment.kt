package com.example.popcorn.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.popcorn.databinding.FragmentHeadlinesBinding
import com.example.popcorn.network.services.TmdbService
import com.google.android.material.tabs.TabLayout


class HeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentHeadlinesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        setupTabLayout()
        setupViewPager()
        return binding.root
    }




private fun setupViewPager() {
    //binding.viewpagerHeadlines.apply {
    //adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, binding.tablayoutHeadlines.tabCount)
    //addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablayoutHeadlines)) }
    val viewPager = binding.viewpagerHeadlines
    val viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
    viewPager.adapter = viewPagerAdapter
    val tablayout = binding.tablayoutHeadlines
    tablayout.setupWithViewPager(binding.viewpagerHeadlines)

}

private fun setupTabLayout() {
    //tablayout.addTab(tablayout.newTab().setText("All Genres"))
    //tablayout.addTab(tablayout.newTab().setText("Action"))
    //tablayout.addTab(tablayout.newTab().setText("Adventure"))
    //tablayout.addTab(tablayout.newTab().setText("Animation"))
    //tablayout.addTab(tablayout.newTab().setText(""))
    //tablayout.apply {
        //addTab(tablayout.newTab().setText("All Genres"))
        //addTab(tablayout.newTab().setText("Action"))
        //addTab(tablayout.newTab().setText("Adventure"))
        //addTab(tablayout.newTab().setText("Animation"))

    // tabGravity = TabLayout.GRAVITY_FILL

        /***
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.position?.let {
                binding.viewpagerHeadlines.currentItem = it
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    })***/
//}

}
    companion object {
        fun newInstance(): HeadlinesFragment {
            return HeadlinesFragment()
        }
    }
}

