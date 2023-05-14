package com.example.popcorn.categories

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popcorn.data_models.Movie
import com.example.popcorn.network.services.ApiClient
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class AdventurePagingSource : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = ApiClient.TMDB.fetchAdventureMovies(position)
            val results = response.results

            LoadResult.Page(
                data = results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}