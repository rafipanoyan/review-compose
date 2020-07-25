package fr.rafoufoun.review.ui.home

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import fr.rafoufoun.review.R
import fr.rafoufoun.review.ReviewsViewModelAmbient
import fr.rafoufoun.review.Screen
import fr.rafoufoun.review.model.ReviewItemModel
import fr.rafoufoun.review.pushScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(scaffoldState: ScaffoldState = remember { ScaffoldState() }) {
    val reviewsLiveData = ReviewsViewModelAmbient.current.reviews
    val reviewsState = reviewsLiveData.observeAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
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
        bodyContent = { modifier ->
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
    Box(gravity = ContentGravity.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}