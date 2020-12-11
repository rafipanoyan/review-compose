package fr.rafoufoun.review.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.Screen
import fr.rafoufoun.review.ui.create.NewReviewScreen
import fr.rafoufoun.review.ui.detail.ReviewDetailScreen
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

    val doBack: () -> Unit = { navController.popBackStack() }
    val onReviewClick: (reviewName: String) -> Unit =
        { reviewName -> navController.navigate(route = Screen.Detail.getRoute(reviewName = reviewName)) }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        builder = {
            composable(Screen.Home.route) {
                HomeScreen(
                    navController,
                    scaffoldState,
                    onReviewClick
                )
            }
            composable(Screen.NewReview.route) {
                NewReviewScreen(scaffoldState, doBack)
            }
            composable(Screen.Detail.route) { navBackStackEntry ->
                ReviewDetailScreen(
                    reviewName = ReviewName(
                        navBackStackEntry.arguments!!.getString(
                            Screen.Detail.Args.name
                        )!!
                    ),
                    onBack = doBack
                )
            }
        }
    )
}