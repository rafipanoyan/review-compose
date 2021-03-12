package fr.rafoufoun.review.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.rafoufoun.review.model.ReviewItemModel

@ExperimentalFoundationApi
@Composable
fun ReviewList(
    reviews: List<ReviewItemModel>, modifier: Modifier = Modifier,
    onReviewClick: (reviewName: String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(reviews) { review ->
            ReviewItem(review, onReviewClick)
        }
    }
}