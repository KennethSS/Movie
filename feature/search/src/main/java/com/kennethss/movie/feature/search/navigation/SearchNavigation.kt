package com.kennethss.movie.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kennethss.movie.feature.search.ui.SearchScreen

const val searchNavigationRoute = "search_route"

fun NavController.navigateSearch(navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen() {
    composable(route = searchNavigationRoute) {
        SearchScreen()
    }
}