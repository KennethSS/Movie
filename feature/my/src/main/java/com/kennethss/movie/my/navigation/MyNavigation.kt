package com.kennethss.movie.my.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kennethss.movie.my.ui.MyScreen

const val myNavigationRoute = "my_route"

fun NavController.navigateMy(navOptions: NavOptions? = null) {
    this.navigate(myNavigationRoute, navOptions)
}

fun NavGraphBuilder.myScreen() {
    composable(route = myNavigationRoute) {
        MyScreen()
    }
}