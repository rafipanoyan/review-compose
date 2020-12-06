package fr.rafoufoun.review.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import fr.rafoufoun.review.R
import fr.rafoufoun.review.ReviewApplication
import fr.rafoufoun.review.Screen
import fr.rafoufoun.review.model.ReviewItemModel
import fr.rafoufoun.review.pushScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen() {
    val reviewsVm =
        viewModel<ReviewsViewModel>(factory = ReviewsViewModel.Factory(ReviewApplication.get().reviewSource.allReviews))
    val reviewsState = reviewsVm.reviews.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Review") }
            )
        },
        floatingActionButton = {
            if (!reviewsState.value.isNullOrEmpty()) {
                FloatingActionButton(
                    onClick = { pushScreen(Screen.NewReview) },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(vectorResource(id = R.drawable.ic_add_24))
                }
            }
        },
        bodyContent = {
            val reviewList = reviewsState.value
            if (reviewList.isNullOrEmpty()) {
                LoadingReviews()
            } else {
                ReviewsContent(reviews = reviewList)
            }
        }
    )
}

@Composable
fun ReviewsContent(reviews: List<ReviewItemModel>) {
    ReviewList(reviews = reviews, modifier = Modifier.fillMaxSize())
}

@Composable
fun LoadingReviews() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}