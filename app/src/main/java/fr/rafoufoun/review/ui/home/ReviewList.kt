package fr.rafoufoun.review.ui.home

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.rafoufoun.review.model.ReviewItemModel

@Composable
fun ReviewList(reviews: List<ReviewItemModel>, modifier: Modifier = Modifier) {
    LazyColumnFor(items = reviews, modifier = modifier) { review ->
        ReviewItem(review)
    }
}