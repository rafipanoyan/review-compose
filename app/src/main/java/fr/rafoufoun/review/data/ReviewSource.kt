package fr.rafoufoun.review.data

import android.app.Application
import com.example.domain.interactor.getReviews
import com.example.domain.interactor.tryCreateReview
import com.example.domain.model.Review
import fr.rafoufoun.data.repo.getReviewsFromDatabase
import fr.rafoufoun.data.repo.insertReviewIntoDatabase
import kotlinx.coroutines.flow.Flow

class ReviewSource private constructor(private val app: Application) {

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