package fr.rafoufoun.review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import androidx.ui.livedata.observeAsState
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.example.domain.model.Review
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.list.ReviewList
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
                ReviewsContent(reviews.value)
            }
        }
    }
}

@Composable
fun ReviewsContent(reviews: List<Review>?) {
    if (reviews.isNullOrEmpty()) {
        CircularProgressIndicator()
    } else {
        ReviewList(reviews = reviews)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        ReviewsContent(listOf(Review(ReviewName("test preview"), listOf())))
    }
}