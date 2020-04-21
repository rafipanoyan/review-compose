package fr.rafoufoun.review.home

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import com.example.domain.model.Review

@Composable
fun ReviewList(reviews: List<Review>, modifier: Modifier = Modifier) {
    AdapterList(data = reviews, modifier = modifier) { review ->
        ReviewItem(review = review)
    }
}