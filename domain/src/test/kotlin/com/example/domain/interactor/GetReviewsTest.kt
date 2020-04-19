package com.example.domain.interactor

import com.example.domain.model.Review
import com.example.domain.repo.IReviewRepo
import com.example.domain.review
import com.example.domain.section
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetReviewsTest {

    @Test
    fun test_map_reviews() = runBlocking {
        val expectedResult = listOf(review(sections = listOf(section())))
        val stubFlow = flow {
            emit(expectedResult)
        }

        val actualResult = getReviews { stubFlow }.first()

        Assert.assertEquals(expectedResult, actualResult)
    }
}