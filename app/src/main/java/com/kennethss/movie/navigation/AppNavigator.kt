package com.kennethss.movie.navigation

import androidx.navigation.NavController
import com.kennethss.movie.feature.main.navigation.MainNavigator
import com.kennethss.movie.feature.movie.detail.navigation.navigateMovieDetail

class AppNavigator(
    private val navController: NavController
) : MainNavigator {
    override fun navigateMovieDetail(id: Int) {
        navController.navigateMovieDetail(id)
    }
}