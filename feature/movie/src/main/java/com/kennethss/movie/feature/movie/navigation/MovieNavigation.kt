package com.kennethss.movie.feature.movie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kennethss.movie.feature.movie.ui.MovieRoute

const val movieNavigationRoute = "movie_route"

fun NavController.navigateMovie(navOptions: NavOptions? = null) {
    this.navigate(movieNavigationRoute, navOptions)
}

fun NavGraphBuilder.movieScreen(
    movieNavigator: MovieNavigator,
) {
    composable(route = movieNavigationRoute) {
        MovieRoute(movieNavigator)
    }
}