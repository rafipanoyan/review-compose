package fr.rafoufoun.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fr.rafoufoun.data.model.ReviewDB
import fr.rafoufoun.data.model.SectionDB

@Database(entities = [ReviewDB::class, SectionDB::class], version = 1, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase() {

    abstract fun reviewDao(): ReviewDao

    fun getReviews() = reviewDao().getReviewsWithSections()

    suspend fun insertReviewWithSections(review: ReviewDB, sections: List<SectionDB>) =
        reviewDao().insertReviewWithSections(review, sections)

    suspend fun deleteReview(review: ReviewDB) = reviewDao().deleteReview(review)

    suspend fun updateSection(section: SectionDB) = reviewDao().updateSection(section)

    companion object {

        @Volatile
        private var INSTANCE: ReviewDatabase? = null

        fun getInstance(ctx: Context): ReviewDatabase =
            INSTANCE ?: Room.databaseBuilder(ctx, ReviewDatabase::class.java, "review.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        db.execSQL("INSERT INTO review VALUES('test FROM DB')")
                        db.execSQL("INSERT INTO section VALUES('test', 'story', 4)")
                        db.execSQL("INSERT INTO section VALUES('test', 'character', 3)")
                    }
                })
                .build()
                .also { INSTANCE = it }
    }
}