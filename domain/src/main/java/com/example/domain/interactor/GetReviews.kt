package com.example.domain.interactor

import com.example.domain.model.Review
import kotlinx.coroutines.flow.Flow

fun getReviews(dataSource: () -> Flow<List<Review>>) = dataSource()
