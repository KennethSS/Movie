package com.solar.movie.remote.service

import com.solar.movie.remote.response.movie.MovieDetailResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 *  Created by Kenneth on 12/30/20
 */
interface MovieService {
    @GET("3/movie/550")
    fun getMovieDetailById(): Single<MovieDetailResponse>

    @GET("3/movie/550")
    suspend fun getMovieDetailByIdFromCoroutine(): MovieDetailResponse
}