package com.kennethss.movie.feature.movie.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.kennethss.movie.domain.movie.movie.MovieThumbnail

@OptIn(ExperimentalPagerApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MovieHomeContentScreen(
    onClickMovieThumbnail: (Int) -> Unit,
    viewModel: MovieHomeContentViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()
    val scrollState = rememberLazyListState()

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp, 800.dp)
    ) {
        item {
            Box {
                HorizontalPager(
                    count = BannerImageSample.count(),
                    state = pagerState
                ) { page ->
                    AsyncImage(
                        model = BannerImageSample[page],
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .align(Alignment.BottomStart),
                    activeColor = Color.White,
                    indicatorWidth = 4.dp
                )
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            MovieRow(
                title = "HOT & NEW",
                movies = when (uiState) {
                    is MovieHomeContentContract.State.Loading -> listOf()
                    is MovieHomeContentContract.State.Success -> (uiState as MovieHomeContentContract.State.Success).movies
                },
                onClickMovieThumbnail = { id -> onClickMovieThumbnail(id) }
            )
        }

        item { Spacer(modifier = Modifier.height(200.dp)) }
    }
}

@Composable
private fun MovieRow(
    title: String,
    movies: List<MovieThumbnail>,
    onClickMovieThumbnail: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies) { movie ->
                MovieThumbnail(
                    id = movie.id,
                    name = movie.name,
                    posterUrl = movie.posterUrl,
                    onClickThumbnail = { id -> onClickMovieThumbnail(id) }
                )
            }
        }
    }
}

@Composable
private fun MovieThumbnail(
    id: Int,
    name: String,
    posterUrl: String,
    onClickThumbnail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClickThumbnail(id) },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            modifier = Modifier
                .width(112.dp)
                .height(162.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = name,
            modifier = Modifier
                .width(112.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

val BannerImageSample = listOf(
    "https://nstore-phinf.pstatic.net/20221206_119/nstore_adm_1670289985042nsj3j_JPEG/%BA%F2%B9%E8%B3%CA_%C1%A4%B9%E6%C7%FC_828X640.jpg?type=w640",
    "https://nstore-phinf.pstatic.net/20221201_265/nstore_adm_1669857010887u397g_JPEG/%BA%F2%B9%E8%B3%CA_%C1%A4%B9%E6%C7%FC_828X640.jpg?type=w640",
    "https://nstore-phinf.pstatic.net/20221207_186/nstore_adm_1670377583835uc2ew_JPEG/%BA%F2%B9%E8%B3%CA_%C1%A4%B9%E6%C7%FC_828X640.jpg?type=w640",
    "https://nstore-phinf.pstatic.net/20221116_38/nstore_adm_1668581439404N44rT_JPEG/%BA%F2%B9%E8%B3%CA_%C1%A4%B9%E6%C7%FC_828X640.jpg?type=w640"
).shuffled()
