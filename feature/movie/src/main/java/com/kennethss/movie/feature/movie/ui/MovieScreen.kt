package com.kennethss.movie.feature.movie.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.kennethss.movie.feature.movie.navigation.MovieNavigator
import com.kennethss.movie.feature.movie.ui.category.MovieCategoryContentScreen
import com.kennethss.movie.feature.movie.ui.event.MovieEventContentScreen
import com.kennethss.movie.feature.movie.ui.free.MovieFreeContentScreen
import com.kennethss.movie.feature.movie.ui.home.MovieHomeContentScreen
import com.kennethss.movie.feature.movie.ui.top.MovieTopContentRoute
import kotlinx.coroutines.launch

@Composable
fun MovieRoute(
    navigator: MovieNavigator,
) {
    MovieScreen(
        navigator = navigator
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieScreen(
    navigator: MovieNavigator,
) {
    val pagerState = rememberPagerState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item { Header() }
        stickyHeader { Tabs(pagerState = pagerState) }
        item {
            TabsContent(
                pagerState = pagerState,
                navigator = navigator
            )
        }
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = HeaderIcon,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(
    pagerState: PagerState,
    navigator: MovieNavigator
) {
    HorizontalPager(
        pageCount = 5,
        modifier = Modifier.fillMaxSize()
            .zIndex(-1f),
        state = pagerState
    ) { page ->
        when (page) {
            0 -> MovieHomeContentScreen(
                onClickMovieThumbnail = { id -> navigator.navigateMovieDetail(id) }
            )
            1 -> MovieFreeContentScreen()
            2 -> MovieTopContentRoute()
            3 -> MovieEventContentScreen()
            4 -> MovieCategoryContentScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Tabs(
    pagerState: PagerState,
) {
    val tabs: List<String> = listOf("영화홈", "무료", "TOP 100", "이벤트", "카테고리")

    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()

        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.fillMaxWidth()
            .zIndex(1f),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[pagerState.currentPage],
                    tabWidth = tabWidths[pagerState.currentPage]
                )
            )
        }
    ) {
        tabs.forEachIndexed { tabIndex, title ->
            Tab(
                selected = pagerState.currentPage == tabIndex,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(tabIndex)
                    }
                },
                modifier = Modifier
                    .height(TabHeight)
            ) {
                Text(
                    text = title,
                    onTextLayout = { textLayoutResult ->
                        tabWidths[tabIndex] =
                            with(density) { textLayoutResult.size.width.toDp() }
                    },
                    fontWeight = if (pagerState.currentPage == tabIndex) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

private fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed {
    composed {
        val currentTabWidth by animateDpAsState(
            targetValue = tabWidth,
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        )
        val indicatorOffset by animateDpAsState(
            targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
            animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
        )
        fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = indicatorOffset)
            .width(currentTabWidth)
    }
}

private val TabHeight = 42.dp
private const val HeaderIcon =
    "https://w.namu.la/s/5b4eec9fbba4e632b95fa81b855c2e5db64393da9ea29376c10658db4d3b9352cf2885610750199c5dd4278ee6d34153b5ce99d812c9bb360d0c8f620090d2d3a5404137410948eb52b3dc68ca936c2b6caa9998e032dc658adaa5cfbafbff11"