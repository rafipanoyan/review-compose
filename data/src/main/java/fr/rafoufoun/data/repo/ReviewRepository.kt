package fr.rafoufoun.data.repo

import android.app.Application
import com.example.domain.model.Review
import fr.rafoufoun.data.db.ReviewDatabase
import fr.rafoufoun.data.model.ReviewAndSections
import fr.rafoufoun.data.model.toReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun getReviews(app: Application): Flow<List<Review>> =
    ReviewDatabase.getInstance(app).getReviews()
        .map { reviewsWithSections ->
            reviewsWithSections.map(ReviewAndSections::toReview)
        }