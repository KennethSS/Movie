package com.kennethss.movie.feature.movie.detail.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kennethss.movie.domain.movie.movie.actorsText
import com.kennethss.movie.domain.movie.movie.directorsText
import com.kennethss.movie.domain.movie.movie.rating
import com.kennethss.movie.domain.movie.movie.releaseYear
import com.kennethss.movie.domain.movie.preview.Preview
import com.kennethss.movie.feature.movie.detail.R
import com.kennethss.movie.feature.movie.detail.player.DefaultYoutubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun MovieDetailRoute(
    movieId: Int,
    onBackClick: () -> Unit
) {
    MovieDetailScreen(movieId, onBackClick)
}

@Composable
private fun YoutubePlayer(
    videoId: String,
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    context: Context = LocalContext.current
) {

    val youtubePlayerView = YouTubePlayerView(context).apply {
        addYouTubePlayerListener(DefaultYoutubePlayerListener(videoId))
    }

    AndroidView(factory = { youtubePlayerView })

    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {

            } else if (event == Lifecycle.Event.ON_STOP) {
                youtubePlayerView.release()
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {

    val uiState = viewModel.stateFlow.collectAsStateWithLifecycle().value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (uiState) {
            is MovieDetailContract.State.Success -> {
                LazyColumn {
                    uiState.movie.previews.firstOrNull { it.type == Preview.Type.TRAILER }
                        .let { preview ->
                            if (preview == null) {
                                item { Poster(uiState.movie.posterUrl) }
                            } else {
                                item { YoutubePlayer(videoId = preview.key) }
                            }
                        }

                    item {
                        MovieDetailInfo(
                            title = uiState.movie.title,
                            rating = uiState.movie.rating(),
                            year = uiState.movie.releaseYear(),
                            runtime = uiState.movie.runtime
                        )
                    }

                    item { CastAndCrew("감독", uiState.movie.directorsText()) }
                    item { Spacer(modifier = Modifier.height(2.dp)) }
                    item { CastAndCrew("주연", uiState.movie.actorsText()) }
                    item { MovieOverView(uiState.movie.overview) }
                }
            }
            else -> {}
        }

        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .clickable { onBackClick() },
            tint = Color.White
        )
    }
}

@Composable
private fun Poster(posterUrl: String) {
    AsyncImage(
        model = posterUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun MovieOverView(overview: String) {
    var expand by remember { mutableStateOf(false) }

    Text(
        text = overview,
        modifier = Modifier
            .padding(16.dp)
            .clickable { expand = !expand },
        overflow = TextOverflow.Ellipsis,
        maxLines = if (expand) Int.MAX_VALUE else 2
    )
}

@Composable
private fun MovieDetailInfo(
    title: String,
    rating: String,
    year: String,
    runtime: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 20.dp,
                end = 16.dp,
                bottom = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_rating),
                        contentDescription = null
                    )
                    Text(rating)
                }

                Text(year)
                Text("${runtime}분")
                Text("12세 관람가")
                Text("DRM")
            }
        }
        Column {
            Icon(
                painter = painterResource(id = R.drawable.ic_outline_add_box_24),
                contentDescription = null
            )

            Text("관심")
        }
    }
}

@Composable
private fun CastAndCrew(
    header: String,
    content: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = header,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = content,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}