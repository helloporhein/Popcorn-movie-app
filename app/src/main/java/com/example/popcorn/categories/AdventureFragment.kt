package com.example.popcorn.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.popcorn.R
import com.example.popcorn.adapters.PagedMoviesAdapter
import com.example.popcorn.data_models.Movie
import com.example.popcorn.databinding.FragmentAdventureBinding
import com.example.popcorn.databinding.FragmentMoviesBinding
import com.example.popcorn.extensions.toMediaBsData
import com.example.popcorn.network.services.TmdbService
import com.example.popcorn.screens.MediaDetailsBottomSheet
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AdventureFragment : Fragment() {

    var ADVENTURE = 12
    private var categoryAdapter: PagedMoviesAdapter? = null
    private val viewModel by viewModels<CategoriesViewModel>()
    private var binding: FragmentAdventureBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdventureBinding.inflate(layoutInflater)
        categoryAdapter = PagedMoviesAdapter(this::handleMovieClick)
        binding!!.adventureList.adapter = categoryAdapter

        fetchData()
        return binding!!.root
    }
    private fun fetchData() {
        lifecycleScope.launch {
            try {
                viewModel.getAdventureMovies().collectLatest {
                    categoryAdapter?.submitData(it)
                }
            } catch (e: Exception) {
            }
        }
    }
    fun handleMovieClick(movie: Movie) {
        MediaDetailsBottomSheet.newInstance(movie.toMediaBsData())
            .show(requireActivity().supportFragmentManager, movie.id.toString())
    }

}