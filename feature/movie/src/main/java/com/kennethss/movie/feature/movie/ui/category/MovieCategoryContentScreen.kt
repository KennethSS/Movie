package com.kennethss.movie.feature.movie.ui.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MovieCategoryContentScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "WIP 💪",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}