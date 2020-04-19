package com.example.domain.repo

import com.example.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface IReviewRepo {

    suspend fun getReviews(): Flow<List<Review>>
}