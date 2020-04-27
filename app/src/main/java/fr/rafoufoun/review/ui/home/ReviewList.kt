package fr.rafoufoun.review.ui.home

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import fr.rafoufoun.review.model.ReviewItemModel

@Composable
fun ReviewList(reviews: List<ReviewItemModel>, modifier: Modifier = Modifier) {
    AdapterList(data = reviews, modifier = modifier) { review ->
        ReviewItem(review = review)
    }
}