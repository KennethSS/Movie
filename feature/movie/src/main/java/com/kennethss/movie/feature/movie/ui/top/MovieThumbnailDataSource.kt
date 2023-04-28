package com.kennethss.movie.feature.movie.ui.top

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kennethss.movie.domain.movie.movie.MovieThumbnail
import com.kennethss.movie.core.usecase.filterNotLoading
import com.kennethss.movie.domain.movie.movie.FetchPopularMovieUseCase
import com.kennethss.movie.domain.movie.movie.FetchPopularMovieUseCaseParam
import com.kennethss.movie.core.usecase.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MovieThumbnailDataSource(
    private val fetchPopularMovieUseCase: FetchPopularMovieUseCase
) : PagingSource<Int, MovieThumbnail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieThumbnail> {
        return try {
            val page = params.key ?: INIT_PAGE

            return fetchPopularMovieUseCase(FetchPopularMovieUseCaseParam(page))
                .filterNotLoading()
                .map { result ->
                    when (result) {
                        is Result.Error -> LoadResult.Error(result.throwable)
                        is Result.Success -> LoadResult.Page(
                            data = result.data,
                            prevKey = (page - 1).takeIf { it >= INIT_PAGE },
                            nextKey = (page + 1).takeIf { true }
                        )
                        else -> throw Exception("")
                    }
                }
                .first()
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieThumbnail>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val INIT_PAGE = 1
    }
}