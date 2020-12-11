package fr.rafoufoun.data.repo

import android.app.Application
import com.example.domain.model.*
import fr.rafoufoun.data.db.ReviewDatabase
import fr.rafoufoun.data.model.ReviewAndSections
import fr.rafoufoun.data.model.toDB
import fr.rafoufoun.data.model.toReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun getReviewFromDatabase(app: Application, reviewName: ReviewName) =
    ReviewDatabase.getInstance(app).getReview(reviewName)
        .map { reviewAndSections ->
            Review(
                ReviewName(reviewAndSections.review.name),
                reviewAndSections.sections.map { sectionDB ->
                    Section(
                        SectionLabel(sectionDB.label),
                        Mark(sectionDB.mark, sectionDB.outOf)
                    )
                }
            )
        }

fun getReviewsFromDatabase(app: Application): Flow<List<Review>> =
    ReviewDatabase.getInstance(app).getReviews()
        .map { reviewsWithSections ->
            reviewsWithSections.map(ReviewAndSections::toReview)
        }

suspend fun insertReviewIntoDatabase(app: Application, review: Review) =
    ReviewDatabase.getInstance(app).insertReviewWithSections(
        review = review.toDB(),
        sections = review.sections.map { it.toDB(review.name) }
    )