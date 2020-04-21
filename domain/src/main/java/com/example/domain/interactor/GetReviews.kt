package com.example.domain.interactor

import com.example.domain.model.Review
import kotlinx.coroutines.flow.Flow

fun getReviews(allReviewsSource: () -> Flow<List<Review>>) = allReviewsSource()