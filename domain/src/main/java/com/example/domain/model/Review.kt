package com.example.domain.model

inline class ReviewName(val name: String)

inline class SectionLabel(val label: String)

inline class Mark(val value: Int)

data class Review(
    val name: ReviewName,
    val sections: List<Section>
)

data class Section(
    val label: SectionLabel,
    val mark: Mark
)