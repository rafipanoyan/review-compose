package com.example.domain.model

data class ReviewName(val value: String)

data class SectionLabel(val value: String)

data class Mark(val value: Int, val outOf: Int)

data class Review(
    val name: ReviewName,
    val sections: List<Section>
)

data class Section(
    val label: SectionLabel,
    val mark: Mark
)

fun List<Int>.findCommonBase() = reduce { acc, value -> acc * value }