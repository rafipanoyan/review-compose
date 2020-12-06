package fr.rafoufoun.review.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.rafoufoun.review.Screen
import fr.rafoufoun.review.ui.create.NewReviewScreen
import fr.rafoufoun.review.ui.home.HomeScreen

@Composable
fun ReviewApp() {
    MaterialTheme {
        AppContent()
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        builder = {
            composable(Screen.Home.route) { HomeScreen(navController, scaffoldState) }
            composable(Screen.NewReview.route) { NewReviewScreen(navController, scaffoldState) }
        }
    )
}