package com.kennethss.movie.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kennethss.movie.feature.main.MainRoute

const val mainNavigationRoute = "main"

fun NavGraphBuilder.mainScreen(
    mainNavigator: MainNavigator
) {
    composable(route = mainNavigationRoute) {
        MainRoute(mainNavigator)
    }
}