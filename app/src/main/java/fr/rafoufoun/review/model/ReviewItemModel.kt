package fr.rafoufoun.review.model

import com.example.domain.model.ReviewName
import com.example.domain.model.Section
import com.example.domain.model.findCommonBase

data class ReviewItemModel(
    val name: ReviewName,
    val mark: Average
)

data class Average(val value: Float, val outOf: Int)

fun List<Section>.average(outOf: Int): Float =
    this
        .takeIf { it.isNotEmpty() }
        ?.let { sections ->
            val commonBase = sections.map { it.mark.outOf }.findCommonBase()
            val sum = sections.fold(0) { sum, section ->
                val multiplier: Int = commonBase.div(section.mark.outOf)
                return@fold sum + (section.mark.value * multiplier)
            }.div(sections.size)

            sum.toFloat() * outOf / commonBase
        } ?: 0f