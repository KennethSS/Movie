package com.kennethss.movie.feature.main.navigation

import androidx.annotation.DrawableRes
import com.kennethss.movie.feature.main.R

enum class MainDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val iconTextId: String
) {
    MOVIE(
        selectedIcon = R.drawable.bottom_nav_movie_selected,
        unselectedIcon = R.drawable.bottom_nav_movie_unselected,
        iconTextId = "영화"
    ),
    SEARCH(
        selectedIcon = R.drawable.bottom_nav_search_selected,
        unselectedIcon = R.drawable.bottom_nav_search_unselected,
        iconTextId = "검색"
    ),
    MY(
        selectedIcon = R.drawable.bottom_nav_my_selected,
        unselectedIcon = R.drawable.bottom_nav_my_unselected,
        iconTextId = "MY"
    )
}