package fr.rafoufoun.review

import android.app.Application
import fr.rafoufoun.data.repo.getReviews

class ReviewApplication : Application() {

    init {
        initialize(this)
    }

    fun getReviewFromDatabase() = getReviews(this)

    companion object {

        private var INSTANCE: ReviewApplication? = null

        private fun initialize(application: ReviewApplication) {
            INSTANCE = application
        }

        fun get(): ReviewApplication =
            INSTANCE ?: throw IllegalStateException("Should have initialized Application instance")
    }
}