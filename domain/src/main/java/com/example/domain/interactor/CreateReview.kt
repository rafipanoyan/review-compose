package com.example.domain.interactor

import com.example.domain.exception.DuplicateReviewException
import com.example.domain.model.Review

suspend fun tryCreateReview(review: Review, insertReview: suspend (review: Review) -> Unit) {
    try {
        insertReview(review)
    } catch (e: Exception) {
        throw DuplicateReviewException()
    }
}