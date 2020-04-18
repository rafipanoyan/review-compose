package fr.rafoufoun.data.db

import androidx.room.Database
import fr.rafoufoun.data.model.ReviewDB
import fr.rafoufoun.data.model.SectionDB

@Database(entities = [ReviewDB::class, SectionDB::class], version = 1, exportSchema = false)
abstract class ReviewDatabase {

    abstract fun reviewDao(): ReviewDao

    suspend fun getReviews() = reviewDao().getReviewsWithSections()

    suspend fun insertReviewWithSections(review: ReviewDB, sections: List<SectionDB>) =
        reviewDao().insertReviewWithSections(review, sections)

    suspend fun deleteReview(review: ReviewDB) = reviewDao().deleteReview(review)

    suspend fun updateSection(section: SectionDB) = reviewDao().updateSection(section)
}