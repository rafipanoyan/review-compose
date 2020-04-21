package fr.rafoufoun.review

import android.app.Application
import fr.rafoufoun.review.provider.ReviewSource

class ReviewApplication : Application() {

    init {
        initialize(this)
    }

    val reviewSource = ReviewSource.get(this)

    companion object {

        private var INSTANCE: ReviewApplication? = null

        private fun initialize(application: ReviewApplication) {
            INSTANCE = application
        }

        fun get(): ReviewApplication =
            INSTANCE ?: throw IllegalStateException("Should have initialized Application instance")
    }
}