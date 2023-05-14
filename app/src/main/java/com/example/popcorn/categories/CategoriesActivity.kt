package com.example.popcorn.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.popcorn.R
import com.example.popcorn.databinding.ActivityCategoriesBinding
import com.example.popcorn.network.services.TmdbService
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesActivity : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private var headlinesFragment: HeadlinesFragment? = null
    private lateinit var binding: ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            // Add a default fragment
            headlinesFragment = HeadlinesFragment.newInstance()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, headlinesFragment!!)
                .commit()
        }
        setupToolbar()
    }
    private fun setupToolbar() { setSupportActionBar(binding.toolbar)
        //val actionBar = supportActionBar
            binding.toolbar.setNavigationOnClickListener { finish() }
        if (actionBar != null) {
            actionBar!!.title = getString(R.string.category)
            //Remove trailing space from toolbar
            binding.toolbar.setContentInsetsAbsolute(10, 10)
        }
    }
    /***
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.categories, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action -> {

                return true
            }
            R.id.adventure -> {

                return true
            }
            R.id.animation -> {

                return true
            }
            R.id.exit -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }***/
}