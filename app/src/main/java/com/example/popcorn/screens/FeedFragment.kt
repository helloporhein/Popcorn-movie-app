package com.example.popcorn.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.popcorn.R
import com.example.popcorn.adapters.*
import com.example.popcorn.categories.CategoriesActivity
//import com.example.mhsflix.adapters.FeedItemsController
import com.example.popcorn.data.FeedViewModel
import com.example.popcorn.data_models.Media
import com.example.popcorn.data_models.Movie
import com.example.popcorn.data_models.TvShow
import com.example.popcorn.databinding.FragmentFeedBinding
import com.example.popcorn.extensions.toMediaBsData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.min

class FeedFragment : BottomNavFragment() {
    private lateinit var binding: FragmentFeedBinding
    private val feedViewModel by viewModels<FeedViewModel>()

    private lateinit var discoverMoviesItemsAdapter: DiscoverMoviesAdapter
    private lateinit var trendMoviesItemsAdapter: TrendMoviesAdapter
    private lateinit var popularMoviesItemsAdapter: FeedMoviesAdapter
    private lateinit var tvShowsItemsAdapter: FeedTvShowsAdapter
    //private lateinit var feedItemsController: FeedItemsController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        fetchData()
        fetchData2()
        fetchData3()
        fetchData4()
        (requireActivity() as BottomNavActivity).onFeedFragmentViewCreated()
    }

    override fun onFirstDisplay() {
        feedViewModel.getPopularMovies()
            ///.observe(viewLifecycleOwner) { feedItemsController.submitList(it) }
    }

    private fun handleSearchClick() {
        val intent = Intent(requireActivity(), SearchActivity::class.java)
        startActivity(intent)
    }

    private fun handleMediaClick(media: Media) {
        if (media is Media.Movie) {
            MediaDetailsBottomSheet.newInstance(media.toMediaBsData())
                .show(requireActivity().supportFragmentManager, media.id.toString())
        } else if (media is Media.Tv) {
            MediaDetailsBottomSheet.newInstance(media.toMediaBsData())
                .show(requireActivity().supportFragmentManager, media.id.toString())
        }
    }

    private fun setupUI() {
        //calculateAndSetListTopPadding()
        binding.searchIcon.setOnClickListener { handleSearchClick() }

        discoverMoviesItemsAdapter = DiscoverMoviesAdapter(this::handleMovieClick)
        binding.headerContainer.adapter = discoverMoviesItemsAdapter
        binding.headerContainer.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val scrollY: Int = binding.postersList.computeVerticalScrollOffset()
                val color = changeAlpha(
                    ContextCompat.getColor(requireActivity(), R.color.black_transparent),
                    (min(255, scrollY).toFloat() / 255.0f).toDouble()
                )
                binding.appBarLayout.setBackgroundColor(color)
            }
        })

        trendMoviesItemsAdapter = TrendMoviesAdapter(this::handleMovieClick)
        binding.postersList.adapter = trendMoviesItemsAdapter
        binding.postersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val scrollY: Int = binding.postersList.computeVerticalScrollOffset()
                val color = changeAlpha(
                    ContextCompat.getColor(requireActivity(), R.color.black_transparent),
                    (min(255, scrollY).toFloat() / 255.0f).toDouble()
                )
                binding.appBarLayout.setBackgroundColor(color)
            }
        })

        popularMoviesItemsAdapter = FeedMoviesAdapter(this::handleMovieClick)
        binding.postersList2.adapter = popularMoviesItemsAdapter
        binding.postersList2.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val scrollY: Int = binding.postersList.computeVerticalScrollOffset()
                val color = changeAlpha(
                    ContextCompat.getColor(requireActivity(), R.color.black_transparent),
                    (min(255, scrollY).toFloat() / 255.0f).toDouble()
                )
                binding.appBarLayout.setBackgroundColor(color)
            }
        })

        tvShowsItemsAdapter = FeedTvShowsAdapter(this::handleTvClick)
        binding.postersList3.adapter = tvShowsItemsAdapter
        binding.postersList3.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val scrollY: Int = binding.postersList.computeVerticalScrollOffset()
                val color = changeAlpha(
                    ContextCompat.getColor(requireActivity(), R.color.black_transparent),
                    (min(255, scrollY).toFloat() / 255.0f).toDouble()
                )
                binding.appBarLayout.setBackgroundColor(color)
            }
        })


        //feedItemsController = FeedItemsController(this::handleMediaClick)
        //binding.feedItemsList.adapter = feedItemsController.adapter

        binding.moreText.setOnClickListener {
            val intent = Intent(requireActivity(), PopularMoviesActivity::class.java)
            startActivity(intent)
        }
        binding.tvShowsTv.setOnClickListener {
            val intent = Intent(requireActivity(), PopularTvActivity::class.java)
            startActivity(intent)
        }

        binding.moviesTv.setOnClickListener {
            val intent = Intent(requireActivity(), PopularMoviesActivity::class.java)
            startActivity(intent)
        }
        binding.categoriesTv.setOnClickListener {
            val intent = Intent(requireActivity(),CategoriesActivity::class.java)
            startActivity(intent)
        }
    }
    private fun fetchData() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
            try {
                feedViewModel.getDiscoverMovies().collectLatest {
                    discoverMoviesItemsAdapter.submitData(it)
                }
            } catch (e: Exception) {
                Log.d("ESDF","${e}")
            }
        }
        }
    }
    private fun fetchData2() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                try {
                    feedViewModel.getTrendingMovies().collectLatest {
                        trendMoviesItemsAdapter.submitData(it)
                    }
                } catch (e: Exception) {
                    Log.d("ESDF", "${e}")
                }
            }
        }
    }
    private fun fetchData3() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                try {

                    feedViewModel.getPopularMovies().collectLatest {
                        popularMoviesItemsAdapter.submitData(it)
                    }

                } catch (e: Exception) {
                    Log.d("ESDF", "${e}")
                }
            }
        }
    }
    private fun fetchData4() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                try {
                    feedViewModel.getTvShows().collectLatest {
                        tvShowsItemsAdapter.submitData(it)
                    }
                } catch (e: Exception) {
                    Log.d("ESDF", "${e}")
                }
            }
        }
    }

    private fun changeAlpha(color: Int, fraction: Double): Int {
        val red: Int = Color.red(color)
        val green: Int = Color.green(color)
        val blue: Int = Color.blue(color)
        val alpha: Int = (Color.alpha(color) * (fraction)).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    fun handleMovieClick(movie: Movie) {
        val data = movie.toMediaBsData()
        if (data.mediaType == "movie") {
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("id", data.mediaId)
            startActivity(intent)
        } else {
            Toast.makeText(requireActivity(),"Error",Toast.LENGTH_LONG).show()
        }
        //MediaDetailsBottomSheet.newInstance(movie.toMediaBsData())
            //.show(requireActivity().supportFragmentManager, movie.id.toString())
    }
    private fun handleTvClick(tvShow: TvShow) {
        MediaDetailsBottomSheet.newInstance(tvShow.toMediaBsData())
            .show(requireActivity().supportFragmentManager, tvShow.id.toString())
    }
}

