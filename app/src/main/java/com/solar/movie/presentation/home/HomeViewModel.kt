package com.solar.movie.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.solar.movie.NetworkState
import com.solar.movie.domain.repository.model.Movie
import com.solar.movie.domain.repository.usecase.MovieUseCase
import com.solar.movie.extension.liveDataScope
import com.solar.movie.presentation.movie.thumb.MovieThumbView

class HomeViewModel @ViewModelInject constructor(
    private val movieUseCase: MovieUseCase
): ViewModel() {

    val popularMovieLiveData: LiveData<NetworkState<List<MovieThumbView>>>

    init {
        popularMovieLiveData = liveDataScope(networkCall = {
            movieUseCase.getPopularMovie()
        }, map = {
            it.map(::transformMovieToPopularThumb)
        })
    }

    private fun transformMovieToPopularThumb(movie: Movie): MovieThumbView  {
        return MovieThumbView(
            id = movie.id,
            poster = movie.poster,
            name = movie.title,
            releaseDate = movie.releaseDate
        )
    }
}