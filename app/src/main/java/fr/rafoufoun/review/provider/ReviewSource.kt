package fr.rafoufoun.review.provider

import android.app.Application
import com.example.domain.interactor.getReviews
import com.example.domain.model.Review
import fr.rafoufoun.data.repo.getReviewsFromDatabase
import kotlinx.coroutines.flow.Flow

class ReviewSource private constructor(private val app: Application) {

    val allReviews: () -> Flow<List<Review>> = {
        getReviews { getReviewsFromDatabase(app) }
    }

    companion object {

        @Volatile
        private var INSTANCE: ReviewSource? = null

        fun get(app: Application): ReviewSource =
            INSTANCE ?: ReviewSource(app).also { INSTANCE = it }
    }
}