package com.example.popcorn.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.popcorn.adapters.PagedMoviesAdapter
import com.example.popcorn.data.MediaViewModel
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.ActivityPopularMoviesBinding
import com.example.popcorn.extensions.toMediaBsData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesActivity : BaseActivity() {
    private lateinit var binding: ActivityPopularMoviesBinding
    private val viewModel by viewModels<MediaViewModel>()
    private lateinit var popularMoviesItemsAdapter: PagedMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopularMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        fetchData()
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        popularMoviesItemsAdapter = PagedMoviesAdapter(this::handleMovieClick)
        binding.popularMoviesList.adapter = popularMoviesItemsAdapter
    }

    private fun fetchData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                try {
                    viewModel.getPopularMovies().collectLatest {
                        popularMoviesItemsAdapter.submitData(it)
                    }
                } catch (e: Exception) {
                }
            }

        }
    }

    fun handleMovieClick(movie: Movie) {
        MediaDetailsBottomSheet.newInstance(movie.toMediaBsData())
            .show(supportFragmentManager, movie.id.toString())
    }
}