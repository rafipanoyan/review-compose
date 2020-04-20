package fr.rafoufoun.review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.livedata.observeAsState
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.example.domain.model.Review
import com.example.domain.model.ReviewName
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val reviewsVm: ReviewsViewModel by lazy {
        ViewModelProvider(
            this,
            ReviewsViewModel.Factory(ReviewApplication.get()::getReviewFromDatabase)
        )[ReviewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val reviews = reviewsVm.reviews.observeAsState()
            MaterialTheme {
                Greeting(reviews.value)
            }
        }
    }
}

@Composable
fun Greeting(reviews: List<Review>?) {
    if (reviews.isNullOrEmpty()) {
        Text(text = "NO VALUE")
    } else {
        Column {
            reviews.forEach { review ->
                Text(text = review.name.value)
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting(listOf(Review(ReviewName("test preview"), listOf())))
    }
}