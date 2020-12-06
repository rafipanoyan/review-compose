package fr.rafoufoun.review.ui.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.model.Average
import fr.rafoufoun.review.model.ReviewItemModel

@Composable
fun ReviewItem(review: ReviewItemModel) {
    val ctx = AmbientContext.current
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