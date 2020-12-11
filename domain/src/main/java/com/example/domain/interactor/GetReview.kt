package com.example.domain.interactor

import com.example.domain.model.Review
import com.example.domain.model.ReviewName
import kotlinx.coroutines.flow.Flow

fun getReview(reviewName: ReviewName, reviewSource: (reviewName: ReviewName) -> Flow<Review>) =
    reviewSource(reviewName)