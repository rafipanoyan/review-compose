package fr.rafoufoun.review.ui.detail

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.viewModel
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.BuildConfig
import fr.rafoufoun.review.ReviewApplication
import fr.rafoufoun.review.model.ReviewModel
import fr.rafoufoun.review.ui.common.LoadingContentFull
import fr.rafoufoun.review.ui.common.ViewModelDebug
import fr.rafoufoun.review.ui.create.NewReviewTopAppBar

@Composable
fun ReviewDetailScreen(reviewName: ReviewName, onBack: () -> Unit) {

    val reviewDetailViewModel: ReviewDetailViewModel =
        viewModel(
            factory = ReviewDetailViewModel.Factory(
                reviewName,
                ReviewApplication.get().reviewSource.reviewDetail
            )
        )

    val review = reviewDetailViewModel.review.observeAsState().value

    Scaffold(
        topBar = {
            NewReviewTopAppBar(onBack)
        }
    ) {
        review?.let {
            ReviewDetailContent(review = it)
        } ?: LoadingContentFull()

        if (BuildConfig.DEBUG) {
            ViewModelDebug(vm = reviewDetailViewModel)
        }
    }
}

@Composable
fun ReviewDetailContent(review: ReviewModel) {
    Text(text = review.name.value)
}