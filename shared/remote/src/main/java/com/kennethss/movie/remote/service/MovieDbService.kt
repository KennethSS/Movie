package com.kennethss.movie.remote.service

import com.kennethss.movie.remote.response.detail.MovieDetailResponse
import com.kennethss.movie.remote.response.popular.MoviePopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbService {
    @GET("3/movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("append_to_response") credits: String = "credits,images,videos"
    ): MovieDetailResponse

    @GET("3/movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int
    ): MoviePopularResponse
}