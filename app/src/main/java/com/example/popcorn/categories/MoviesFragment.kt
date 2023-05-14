package com.example.popcorn.categories

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.popcorn.R
import com.example.popcorn.adapters.PagedMoviesAdapter
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.FragmentMoviesBinding
import com.example.popcorn.extensions.toMediaBsData
import com.example.popcorn.screens.MediaDetailsBottomSheet
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MoviesFragment : Fragment() {
    private var categoryAdapter: PagedMoviesAdapter? = null
    private val viewModel by viewModels<CategoriesViewModel>()
    private var binding: FragmentMoviesBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        categoryAdapter = PagedMoviesAdapter(this::handleMovieClick)
        binding!!.popularMoviesList.adapter = categoryAdapter
        setHasOptionsMenu(true)
        fetchData()
        return binding!!.root
    }
    private fun fetchData() {
        lifecycleScope.launch {
            try {
                viewModel.getPopularMovies().collectLatest {
                    categoryAdapter?.submitData(it)
                }
            } catch (e: Exception) {
            }
        }
    }
    private fun fetchData1() {
        lifecycleScope.launch {
            try {
                viewModel.getActionMovies().collectLatest {
                    categoryAdapter?.submitData(it)
                }
            } catch (e: Exception) {
            }
        }
    }
    private fun fetchData2() {
        lifecycleScope.launch {
            try {
                viewModel.getAdventureMovies().collectLatest {
                    categoryAdapter?.submitData(it)
                }
            } catch (e: Exception) {
            }
        }
    }
    private fun fetchData3() {
        lifecycleScope.launch {
            try {
                viewModel.getAnimationMovies().collectLatest {
                    categoryAdapter?.submitData(it)
                }
            } catch (e: Exception) {
            }
        }
    }
    private fun handleMovieClick(movie: Movie) {
        MediaDetailsBottomSheet.newInstance(movie.toMediaBsData())
            .show(requireActivity().supportFragmentManager, movie.id.toString())
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        // TODO Add your menu entries here
        inflater.inflate(R.menu.categories, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action ->
                fetchData1()
                // Not implemented here
                //return false
            R.id.adventure ->
                    fetchData2()
                // Do Fragment menu item stuff here
                //return true
            R.id.animation ->
                    fetchData3()
            else -> {}
        }
        return false
    }
}