package com.example.popcorn.categories

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.popcorn.data.MediaRepository
import com.example.popcorn.data_models.Movie
import com.example.popcorn.util.ApiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesViewModel @Inject constructor() : ViewModel() {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return MediaRepository.getPopularMoviesStream()
    }

    fun getActionMovies(): Flow<PagingData<Movie>> {
        return CategoriesRepository.getActionMovies()
    }

    fun getAdventureMovies(): Flow<PagingData<Movie>> {
        return CategoriesRepository.getAdventureMovies()
    }

    fun getAnimationMovies(): Flow<PagingData<Movie>> {
        return CategoriesRepository.getAnimationMovies()
    }
}