package com.kennethss.movie.feature.movie.ui.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.kennethss.movie.domain.movie.movie.MovieThumbnail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieTopContentRoute(
    viewModel: MovieTopContentViewModel = hiltViewModel()
) {
    MovieTopContentScreen(
        movieThumbnails = viewModel.movieRowPage.collectAsLazyPagingItems()
    )
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieTopContentScreen(
    movieThumbnails: LazyPagingItems<MovieThumbnail>
) {
    var refreshing by remember { mutableStateOf(false) }
    val refreshScope = rememberCoroutineScope()
    var itemCount by remember { mutableStateOf(15) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        itemCount += 5
        refreshing = false
    }

    val refreshState = rememberPullRefreshState(refreshing, onRefresh = ::refresh)

    Box(
        modifier = Modifier
            .pullRefresh(refreshState)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(0.dp, 800.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (!refreshing) {
                items(movieThumbnails.itemCount) { index ->
                    MovieItemRow(
                        index = index,
                        movie = movieThumbnails[index]!!,
                        onClickThumbnail = { }
                    )
                }

                if (movieThumbnails.loadState.append == LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(12.dp),
                            )
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
private fun MovieItemRow(
    index: Int,
    movie: MovieThumbnail,
    onClickThumbnail: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClickThumbnail(movie.id) },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = null,
            modifier = Modifier
                .width(112.dp)
                .height(162.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(
                text = index.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = movie.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}