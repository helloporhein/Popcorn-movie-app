package com.example.popcorn.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.popcorn.data_models.FeedItem
import com.example.popcorn.data_models.Movie
import com.example.popcorn.data_models.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(): ViewModel() {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return MediaRepository.getPopularMoviesStream()
    }

    fun getDiscoverMovies(): Flow<PagingData<Movie>> {
        return MediaRepository.getDiscoverMoviesStream()
    }

    fun getTrendingMovies(): Flow<PagingData<Movie>> {
        return MediaRepository.getTrendingMoviesStream()
    }
    fun getTvShows() : Flow<PagingData<TvShow>> {
        return MediaRepository.getPopularTvShowsStream()
    }

    private fun getPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(10)
            .build()
    }
}