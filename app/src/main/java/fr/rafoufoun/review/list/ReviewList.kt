package fr.rafoufoun.review.list

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import com.example.domain.model.Review

@Composable
fun ReviewList(reviews: List<Review>) {
    VerticalScroller(modifier = Modifier.fillMaxWidth()) {
        Column {
            reviews.forEach { ReviewItem(review = it) }
        }
    }
}