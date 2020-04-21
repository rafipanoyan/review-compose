package fr.rafoufoun.review.home

import android.widget.Toast
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Surface
import androidx.ui.material.ripple.ripple
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.domain.model.Review
import com.example.domain.model.ReviewName

@Composable
fun ReviewItem(review: Review) {
    val ctx = ContextAmbient.current
    Clickable(
        onClick = {
            Toast.makeText(ctx, "clicked", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier.ripple()
    ) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = review.name.value,
                modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun ReviewItemPreview() {
    ReviewItem(
        Review(
            ReviewName("review preview"),
            emptyList()
        )
    )
}