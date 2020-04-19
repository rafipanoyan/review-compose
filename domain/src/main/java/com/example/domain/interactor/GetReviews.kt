package com.example.domain.interactor

import com.example.domain.model.Review
import kotlinx.coroutines.flow.Flow

suspend fun getReviews(dataSource: suspend () -> Flow<List<Review>>) = dataSource()
