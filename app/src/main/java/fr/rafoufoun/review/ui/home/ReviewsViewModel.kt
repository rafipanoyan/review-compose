package fr.rafoufoun.review.ui.home

import androidx.lifecycle.*
import com.example.domain.model.Review
import fr.rafoufoun.review.model.ReviewItemModel
import fr.rafoufoun.review.model.average
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

private const val GENERAL_OUT_OF = 5

@ExperimentalCoroutinesApi
class ReviewsViewModel(allReviewsSource: () -> Flow<List<Review>>) : ViewModel() {

    val reviews: LiveData<List<ReviewItemModel>> = allReviewsSource()
        .onStart {
            delay(2000)
        }
        .asLiveData()
        .map { reviews ->
            reviews.map {
                ReviewItemModel(
                    it.name,
                    fr.rafoufoun.review.model.Average(
                        it.sections.average(GENERAL_OUT_OF),
                        GENERAL_OUT_OF
                    )
                )
            }
        }

    class Factory(private val allReviewsSource: () -> Flow<List<Review>>) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass
                .takeIf {
                    it.isAssignableFrom(ReviewsViewModel::class.java)
                }
                ?.let {
                    ReviewsViewModel(allReviewsSource) as T
                }
                ?: throw IllegalArgumentException()

    }
}