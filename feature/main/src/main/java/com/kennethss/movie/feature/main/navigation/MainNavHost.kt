package com.kennethss.movie.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kennethss.movie.feature.movie.navigation.movieNavigationRoute
import com.kennethss.movie.feature.movie.navigation.movieScreen
import com.kennethss.movie.feature.search.navigation.searchScreen
import com.kennethss.movie.my.navigation.myScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    mainNavigator: MainNavigator,
    modifier: Modifier = Modifier,
    startDestination: String = movieNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        movieScreen(mainNavigator)
        searchScreen()
        myScreen()
    }
}