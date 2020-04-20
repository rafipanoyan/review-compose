package fr.rafoufoun.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.domain.model.Review
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class ReviewsViewModel(getReviews: () -> Flow<List<Review>>) : ViewModel() {

    val reviews: LiveData<List<Review>> = getReviews()
        .onStart {
            delay(2000)
        }
        .asLiveData()

    class Factory(private val getReviews: () -> Flow<List<Review>>) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            modelClass
                .takeIf {
                    it.isAssignableFrom(ReviewsViewModel::class.java)
                }
                ?.let {
                    ReviewsViewModel(getReviews) as T
                }
                ?: throw IllegalArgumentException()

    }
}