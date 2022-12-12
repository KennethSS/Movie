package com.kennethss.movie.feature.movie.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kennethss.movie.feature.movie.detail.ui.MovieDetailRoute

const val movieDetailNavigationRoute = "movie_detail_route"

fun NavController.navigateMovieDetail(
    movieId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate("$movieDetailNavigationRoute/$movieId", navOptions)
}

fun NavGraphBuilder.movieDetailScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "$movieDetailNavigationRoute/{movieId}",
        arguments = listOf(navArgument(KEY_MOVIE_ID) { type = NavType.IntType })
    ) { backStackEntry ->
        MovieDetailRoute(
            movieId = backStackEntry.arguments?.getInt(KEY_MOVIE_ID) ?: 0,
            onBackClick = onBackClick
        )
    }
}

const val KEY_MOVIE_ID = "movieId"