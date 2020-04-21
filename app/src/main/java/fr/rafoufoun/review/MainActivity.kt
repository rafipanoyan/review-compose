package fr.rafoufoun.review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Providers
import androidx.compose.ambientOf
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import fr.rafoufoun.review.home.ReviewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
val ReviewsViewModelAmbient = ambientOf<ReviewsViewModel>()

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val reviewsVm: ReviewsViewModel by lazy {
        ViewModelProvider(
            this,
            ReviewsViewModel.Factory(ReviewApplication.get().reviewSource.allReviews)
        )[ReviewsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Providers(ReviewsViewModelAmbient provides reviewsVm) {
                MaterialTheme {
                    ReviewApp()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPress()) {
            super.onBackPressed()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        //ReviewApp()
    }
}