package com.kennethss.movie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kennethss.movie.feature.main.navigation.mainNavigationRoute
import com.kennethss.movie.feature.main.navigation.mainScreen
import com.kennethss.movie.feature.movie.detail.navigation.movieDetailScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = mainNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        mainScreen(AppNavigator(navController))
        movieDetailScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
}