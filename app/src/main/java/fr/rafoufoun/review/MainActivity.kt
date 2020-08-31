package fr.rafoufoun.review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.ui.tooling.preview.Preview
import fr.rafoufoun.review.ui.ReviewApp
import fr.rafoufoun.review.ui.create.ReviewFormViewModel
import fr.rafoufoun.review.ui.home.ReviewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
val ReviewsViewModelAmbient = ambientOf<ReviewsViewModel>()

val ReviewFormViewModelAmbient = ambientOf<ReviewFormViewModel>()

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val reviewsVm: ReviewsViewModel by lazy {
        ViewModelProvider(
            this,
            ReviewsViewModel.Factory(ReviewApplication.get().reviewSource.allReviews)
        )[ReviewsViewModel::class.java]
    }

    private val reviewFormVm: ReviewFormViewModel by lazy {
        ViewModelProvider(
            this,
            ReviewFormViewModel.Factory(ReviewApplication.get().reviewSource.createReview)
        )[ReviewFormViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Providers(
                ReviewsViewModelAmbient provides reviewsVm,
                ReviewFormViewModelAmbient provides reviewFormVm
            ) {
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