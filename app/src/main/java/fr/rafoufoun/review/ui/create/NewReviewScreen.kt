package fr.rafoufoun.review.ui.create

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar

@Composable
fun NewReviewScreen(scaffoldState: ScaffoldState = ScaffoldState()) {
    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(title = { Text(text = "New Review") })
        },
        bodyContent = { CreateReviewForm() }
    )
}

@Composable
fun CreateReviewForm() {
    Text(text = "Create your review")
}