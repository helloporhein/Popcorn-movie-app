package com.example.popcorn.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.popcorn.adapters.PagedTvShowsAdapter
import com.example.popcorn.data.MediaViewModel
import com.example.popcorn.data_models.TvShow
import com.example.popcorn.databinding.ActivityPopularTvBinding
import com.example.popcorn.extensions.toMediaBsData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularTvActivity : BaseActivity() {
    private lateinit var binding: ActivityPopularTvBinding
    private val viewModel by viewModels<MediaViewModel>()
    private lateinit var popularTvItemsAdapter: PagedTvShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPopularTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        fetchData()
    }

    private fun handleTvClick(tvShow: TvShow) {
        MediaDetailsBottomSheet.newInstance(tvShow.toMediaBsData())
            .show(supportFragmentManager, tvShow.id.toString())
    }

    private fun setupUI() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        popularTvItemsAdapter = PagedTvShowsAdapter(this::handleTvClick)
        binding.popularTvList.adapter = popularTvItemsAdapter
    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                viewModel.getPopularTvShows().collectLatest {
                    popularTvItemsAdapter.submitData(it)
                }
            } catch (e: Exception) {
            }
        }
    }
}
