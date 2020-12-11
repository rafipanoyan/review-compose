package fr.rafoufoun.review.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.domain.model.Review
import com.example.domain.model.ReviewName
import fr.rafoufoun.review.model.ReviewModel
import fr.rafoufoun.review.model.SectionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReviewDetailViewModel(
    reviewName: ReviewName,
    reviewDetail: (reviewName: ReviewName) -> Flow<Review>
) : ViewModel() {

    val review = reviewDetail(reviewName).map { review ->
        ReviewModel(
            review.name,
            review.sections.map { section ->
                SectionModel(section.label, section.mark)
            }
        )
    }.asLiveData()

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val reviewName: ReviewName,
        private val reviewDetail: (reviewName: ReviewName) -> Flow<Review>
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            if (modelClass.isAssignableFrom(ReviewDetailViewModel::class.java)) {
                ReviewDetailViewModel(reviewName, reviewDetail) as T
            } else {
                throw IllegalArgumentException()
            }
    }
}