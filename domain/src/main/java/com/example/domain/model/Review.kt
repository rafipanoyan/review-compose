package com.example.domain.model

inline class ReviewName(val value: String)

inline class SectionLabel(val value: String)

data class Mark(val value: Int, val outOf: Int)

data class Review(
    val name: ReviewName,
    val sections: List<Section>
)

data class Section(
    val label: SectionLabel,
    val mark: Mark
)