package com.solar.movie.remote.service

import com.solar.movie.remote.response.PageResponse
import com.solar.movie.remote.response.movie.MovieDetailResponse
import com.solar.movie.remote.response.movie.popular.MoviePopularItemResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  Created by Kenneth on 12/30/20
 */
interface MovieService {
    @GET("3/movie/550")
    fun getMovieDetailById(): Single<MovieDetailResponse>

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetailByIdFromCoroutine(@Path("movieId") movieId: Int): MovieDetailResponse

    @GET("3/movie/popular")
    suspend fun getPopularMovie(): PageResponse<MoviePopularItemResponse>
}