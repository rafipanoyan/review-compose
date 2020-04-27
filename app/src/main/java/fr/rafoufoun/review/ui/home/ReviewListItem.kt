package fr.rafoufoun.review.ui.home

import android.widget.Toast
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Surface
import androidx.ui.material.ripple.ripple
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.model.Average
import fr.rafoufoun.review.model.ReviewItemModel

@Composable
fun ReviewItem(review: ReviewItemModel) {
    val ctx = ContextAmbient.current
    Clickable(
        onClick = {
            Toast.makeText(ctx, "clicked", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier.ripple()
    ) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Row {
                Text(
                    text = review.name.value,
                    modifier = Modifier.padding(16.dp) + Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun ReviewItemPreview() {
    ReviewItem(
        ReviewItemModel(
            ReviewName("review preview"),
            Average(4f, 5)
        )
    )
}