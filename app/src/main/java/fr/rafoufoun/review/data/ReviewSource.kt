package fr.rafoufoun.review.data

import android.app.Application
import com.example.domain.interactor.getReview
import com.example.domain.interactor.getReviews
import com.example.domain.interactor.tryCreateReview
import com.example.domain.model.Review
import com.example.domain.model.ReviewName
import fr.rafoufoun.data.repo.getReviewFromDatabase
import fr.rafoufoun.data.repo.getReviewsFromDatabase
import fr.rafoufoun.data.repo.insertReviewIntoDatabase
import kotlinx.coroutines.flow.Flow

class ReviewSource private constructor(private val app: Application) {

    val reviewDetail: (reviewName: ReviewName) -> Flow<Review> = { reviewName ->
        getReview(reviewName = reviewName) { _reviewName ->
            getReviewFromDatabase(app, _reviewName)
        }
    }

    val allReviews: () -> Flow<List<Review>> = {
        getReviews { getReviewsFromDatabase(app) }
    }

    val createReview: suspend (Review) -> Unit = { review ->
        tryCreateReview(review) { reviewToInsert ->
            insertReviewIntoDatabase(
                app,
                review = reviewToInsert
            )
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: ReviewSource? = null

        fun get(app: Application): ReviewSource =
            INSTANCE
                ?: ReviewSource(app)
                    .also { INSTANCE = it }
    }
}