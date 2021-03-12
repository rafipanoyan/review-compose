package fr.rafoufoun.review.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import fr.rafoufoun.review.R
import fr.rafoufoun.review.ReviewApplication
import fr.rafoufoun.review.Screen
import fr.rafoufoun.review.model.ReviewItemModel
import fr.rafoufoun.review.ui.common.LoadingContentFull

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    onReviewClick: (reviewName: String) -> Unit
) {
    val reviewsVm =
        viewModel<ReviewsViewModel>(factory = ReviewsViewModel.Factory(ReviewApplication.get().reviewSource.allReviews))
    val reviews = reviewsVm.reviews.observeAsState().value

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Review") }
            )
        },
        floatingActionButton = {
            if (!reviews.isNullOrEmpty()) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.NewReview.route) },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_24),
                        contentDescription = null
                    )
                }
            }
        }) {
        if (reviews.isNullOrEmpty()) {
            LoadingContentFull()
        } else {
            ReviewsContent(reviews, onReviewClick)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ReviewsContent(
    reviews: List<ReviewItemModel>,
    onReviewClick: (reviewName: String) -> Unit
) {
    ReviewList(reviews = reviews, modifier = Modifier.fillMaxSize(), onReviewClick)
}