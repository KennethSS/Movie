package com.kennethss.movie.feature.movie.ui.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import com.kennethss.movie.core.mvi.MviReducer
import com.kennethss.movie.domain.movie.movie.FetchPopularMovieUseCase
import com.kennethss.movie.domain.movie.movie.MovieThumbnail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MovieTopContentViewModel @Inject constructor(
    fetchPopularMovieUseCase: FetchPopularMovieUseCase
) : ViewModel() {

    private var firstEpisode: MovieThumbnail? = null

    private val reducer =
        MviReducer<MovieTopContentContract.Event, MovieTopContentContract.State, MovieTopContentContract.Effect>(
            viewModelScope = viewModelScope,
            initialState = MovieTopContentContract.State.initialState(),
            handleEvent = ::handleEvent
        )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    val movieRowPage: Flow<PagingData<MovieThumbnail>> = Pager(
        PagingConfig(20)
    ) {
        MovieThumbnailDataSource(fetchPopularMovieUseCase)
    }.flow.map { data ->
        data.insertSeparators { before, after ->
            if (before == null) {
                firstEpisode = after
            }
            null
        }
    }.cachedIn(viewModelScope)


    private fun handleEvent(event: MovieTopContentContract.Event) {

    }
}