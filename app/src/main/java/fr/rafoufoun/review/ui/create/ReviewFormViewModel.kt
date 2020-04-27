package fr.rafoufoun.review.ui.create

import androidx.lifecycle.*
import com.example.domain.exception.DuplicateReviewException
import com.example.domain.model.Review
import kotlinx.coroutines.launch

sealed class ReviewFormResult {
    object None : ReviewFormResult()
    object DuplicateReviewError : ReviewFormResult()
    object Success : ReviewFormResult()
}

class ReviewFormViewModel(private val createReview: suspend (Review) -> Unit) : ViewModel() {

    private val _formResult = MutableLiveData<ReviewFormResult>(ReviewFormResult.None)
    val formResult: LiveData<ReviewFormResult>
        get() = _formResult

    fun createReview(review: NewReviewModel) {
        viewModelScope.launch {
            try {
                createReview(review.toReview())
                _formResult.value = ReviewFormResult.Success
            } catch (e: DuplicateReviewException) {
                _formResult.value = ReviewFormResult.DuplicateReviewError
            }
        }
    }

    class Factory(private val createReview: suspend (Review) -> Unit) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            if (modelClass.isAssignableFrom(ReviewFormViewModel::class.java)) {
                ReviewFormViewModel(createReview) as T
            } else {
                throw IllegalArgumentException()
            }

    }
}