package fr.rafoufoun.review.ui

import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import fr.rafoufoun.review.ReviewStatus
import fr.rafoufoun.review.Screen
import fr.rafoufoun.review.ui.create.NewReviewScreen
import fr.rafoufoun.review.ui.home.HomeScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun ReviewApp() {
    MaterialTheme {
        AppContent()
    }
}

@ExperimentalCoroutinesApi
@Composable
fun AppContent() {
    Crossfade(current = ReviewStatus.currentScreen) { screen ->
        when (screen) {
            Screen.Home -> HomeScreen()
            is Screen.ReviewDetail -> TODO()
            Screen.NewReview -> NewReviewScreen()
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar {

    }
}