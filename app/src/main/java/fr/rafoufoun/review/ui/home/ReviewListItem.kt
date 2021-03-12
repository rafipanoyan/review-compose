package fr.rafoufoun.review.ui.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.model.Average
import fr.rafoufoun.review.model.ReviewItemModel

@ExperimentalFoundationApi
@Composable
fun ReviewItem(
    review: ReviewItemModel,
    onReviewClick: (reviewName: String) -> Unit
) {
    Log.d("DEBUG", "review composed ${review.name}")
    val popupOpened = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                true,
                onClick = {
                    onReviewClick(review.name.value)
                },
                onLongClick = {
                    popupOpened.value = true
                }
            )
    ) {
        Box {
            Text(
                text = review.name.value,
                modifier = Modifier
                    .padding(16.dp)
                    .then(Modifier.fillMaxWidth())
            )

            if (popupOpened.value) {
                Popup(onDismissRequest = {
                    popupOpened.value = false
                }) {
                    Text(text = "Popup Item")
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ReviewItemPreview() {
    ReviewItem(
        ReviewItemModel(
            ReviewName("review preview"),
            Average(4f, 5)
        )
    ) { }
}