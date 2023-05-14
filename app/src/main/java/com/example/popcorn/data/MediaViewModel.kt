package com.example.popcorn.data

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.popcorn.data_models.Movie
import com.example.popcorn.data_models.TvShow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaViewModel @Inject constructor() : ViewModel() {

    fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return MediaRepository.getUpcomingMovies()
    }

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return MediaRepository.getPopularMoviesStream()
    }

    fun getPopularTvShows(): Flow<PagingData<TvShow>> {
        return MediaRepository.getPopularTvShowsStream()
    }
}