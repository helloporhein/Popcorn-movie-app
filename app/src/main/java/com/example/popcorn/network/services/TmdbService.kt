package com.example.popcorn.network.services

import com.example.popcorn.data_models.Movie
import com.example.popcorn.data_models.TvShow
import com.example.popcorn.network.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("discover/movie")
    suspend fun fetchDiscoverMovies(@Query("page")page: Int): PageResponse<Movie>

    @GET("discover/movie?&with_genres=28")
    suspend fun fetchActionMovies(@Query("page")page: Int): PageResponse<Movie>

    @GET("discover/movie?&with_genres=12")
    suspend fun fetchAdventureMovies(@Query("page")page: Int): PageResponse<Movie>

    @GET("discover/movie?&with_genres=16")
    suspend fun fetchAnimationMovies(@Query("page")page: Int): PageResponse<Movie>

    @GET("movie/now_playing")
    suspend fun fetchTrendingMovies(@Query("page") page: Int): PageResponse<Movie>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(@Query("page") page: Int): PageResponse<Movie>

    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): PageResponse<Movie>

    @GET("movie/{id}?append_to_response=similar,videos")
    suspend fun fetchMovieDetails(@Path("id") movieId: Int): MovieDetailsResponse

    @GET("movie/{id}/similar")
    suspend fun fetchSimilarMovies(@Path("id") movieId: Int): PageResponse<Movie>

    @GET("movie/{id}/videos")
    suspend fun fetchMovieVideos(@Path("id") movieId: Int): VideosResponse

    @GET("tv/popular")
    suspend fun fetchPopularTvShows(@Query("page") page: Int): PageResponse<TvShow>

    @GET("tv/top_rated")
    suspend fun fetchTopRatedTvs(): PageResponse<TvShow>

    @GET("tv/{id}?append_to_response=similar,videos")
    suspend fun fetchTvDetails(@Path("id") tvId: Int): TvDetailsResponse

    @GET("tv/{id}/similar")
    suspend fun fetchSimilarTvs(@Path("id") tvId: Int): PageResponse<TvShow>

    @GET("tv/{id}/videos")
    suspend fun fetchTvVideos(@Path("id") tvId: Int): VideosResponse

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun fetchTvSeasonDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int
    ): TvSeasonDetailsResponse

    @GET("search/multi")
    suspend fun fetchSearchResults(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MediaResponse

    enum class Genres(val title: String){
        action("Action"),adventure("Adventure"),animation("Animation"),comedy("Comedy"),crime("Crime"),documentary("Documentary"),drama("Drama"), family("Family"), fantasy("Fantasy"), history("History"), horror("Horror"), music("Music"), mystery("Mystery"), romance("Romance"), science_fiction("Science Fiction"), tv_movie("TV Movie"), thriller("Thriller"), war("War"), western("Western")
    }
    enum class GenresId(val id: Int){
        action(28),
        adventure(12),
        animation(16),
        comedy(35),
        crime(80),
        documentary(99),
        drama(18),
        family(10751),
        fantasy(14),
        history(36),
        horror(27),
        music(10402),
        mystery(9648),
        romance(10749),
        science_fiction(878),
        tv_movie(10770),
        thriller(53),
        war(10752),
        western(37)
    }
}