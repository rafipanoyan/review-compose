package fr.rafoufoun.data.repo

import android.app.Application
import com.example.domain.model.Review
import com.example.domain.repo.IReviewRepo
import fr.rafoufoun.data.db.ReviewDatabase
import fr.rafoufoun.data.model.ReviewAndSections
import fr.rafoufoun.data.model.toReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReviewRepository(private val app: Application) : IReviewRepo {

    override suspend fun getReviews(): Flow<List<Review>> =
        ReviewDatabase.getInstance(app).getReviews()
            .map { reviewsWithSections ->
                reviewsWithSections.map(ReviewAndSections::toReview)
            }
}