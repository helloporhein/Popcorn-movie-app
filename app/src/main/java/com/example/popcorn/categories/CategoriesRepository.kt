package com.example.popcorn.categories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.popcorn.data.*
import com.example.popcorn.data_models.Movie
import com.example.popcorn.data_models.TvShow
import com.example.popcorn.network.services.ApiClient
import kotlinx.coroutines.flow.Flow

val defaultPagingConfig = PagingConfig(
    pageSize = CategoriesRepository.NETWORK_PAGE_SIZE,
    enablePlaceholders = false
)

object CategoriesRepository {
    private var actionMovies: Flow<PagingData<Movie>>? = null
    private var adventureMovies: Flow<PagingData<Movie>>? = null
    private var animationMovies: Flow<PagingData<Movie>>? = null

    fun getActionMovies(): Flow<PagingData<Movie>> {
        if (actionMovies != null) {
            return actionMovies!!
        }
        actionMovies = Pager(
            config = defaultPagingConfig,
            pagingSourceFactory = { ActionPagingSource() }).flow
        return actionMovies!!
    }

    fun getAdventureMovies(): Flow<PagingData<Movie>> {
        if (adventureMovies != null) {
            return adventureMovies!!
        }
        adventureMovies = Pager(
            config = defaultPagingConfig,
            pagingSourceFactory = { AdventurePagingSource() }).flow
        return adventureMovies!!
    }

    fun getAnimationMovies(): Flow<PagingData<Movie>> {
        if (animationMovies != null) {
            return animationMovies!!
        }
        animationMovies = Pager(
            config = defaultPagingConfig,
            pagingSourceFactory = { AnimationPagingSource() }).flow
        return animationMovies !!
    }


    const val NETWORK_PAGE_SIZE = 20
}
