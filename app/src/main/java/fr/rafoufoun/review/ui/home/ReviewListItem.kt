package fr.rafoufoun.review.ui.home

import android.widget.Toast
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.model.Average
import fr.rafoufoun.review.model.ReviewItemModel

@Composable
fun ReviewItem(review: ReviewItemModel) {
    val ctx = ContextAmbient.current
    Surface(modifier = Modifier.fillMaxWidth().then(Modifier.clickable(onClick = {
        Toast.makeText(ctx, "clicked", Toast.LENGTH_SHORT).show()
    }))) {
        Row {
            Text(
                text = review.name.value,
                modifier = Modifier.padding(16.dp).then(Modifier.fillMaxWidth())
            )
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