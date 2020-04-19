package fr.rafoufoun.data.db

import androidx.room.*
import fr.rafoufoun.data.model.ReviewAndSections
import fr.rafoufoun.data.model.ReviewDB
import fr.rafoufoun.data.model.SectionDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Transaction
    @Query("SELECT * FROM review")
    suspend fun getReviewsWithSections(): Flow<List<ReviewAndSections>>

    @Insert
    suspend fun insertReviewWithSections(review: ReviewDB, sections: List<SectionDB>)

    @Delete
    suspend fun deleteReview(review: ReviewDB)

    @Update
    suspend fun updateSection(section: SectionDB)
}