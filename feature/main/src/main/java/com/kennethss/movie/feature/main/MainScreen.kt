package com.kennethss.movie.feature.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kennethss.movie.feature.main.navigation.MainDestination
import com.kennethss.movie.feature.main.navigation.MainNavHost
import com.kennethss.movie.feature.main.navigation.MainNavigator
import com.kennethss.movie.feature.movie.navigation.navigateMovie
import com.kennethss.movie.feature.search.navigation.navigateSearch
import com.kennethss.movie.my.navigation.navigateMy

@Composable
fun MainRoute(
    mainNavigator: MainNavigator
) {
    MainScreen(
        mainNavigator = mainNavigator
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(
    mainNavigator: MainNavigator
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            MainBottomBar(
                destinations = MainDestination.values().asList(),
                onNavigateToDestination = { mainDestination ->
                    val topLevelNavOptions = navOptions {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }

                    when (mainDestination) {
                        MainDestination.MOVIE -> navController.navigateMovie(topLevelNavOptions)
                        MainDestination.SEARCH -> navController.navigateSearch(topLevelNavOptions)
                        MainDestination.MY -> navController.navigateMy(topLevelNavOptions)

                    }
                },
                currentDestination = navController.currentBackStackEntryAsState().value?.destination,
                modifier = Modifier.testTag("MainBottomBar")
            )
        }
    ) { padding ->
        MainNavHost(
            navController = navController,
            onBackClick = { },
            mainNavigator = mainNavigator,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}

@Composable
private fun MainBottomBar(
    destinations: List<MainDestination>,
    onNavigateToDestination: (MainDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = MainNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            MainNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null
                    )
                },
                label = { Text(destination.iconTextId) }
            )

        }
    }
}

@Composable
private fun RowScope.MainNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MainNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MainNavigationDefaults.navigationContentColor(),
            selectedTextColor = MainNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MainNavigationDefaults.navigationContentColor(),
            indicatorColor = MainNavigationDefaults.navigationIndicatorColor()
        )
    )
}

object MainNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: MainDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false