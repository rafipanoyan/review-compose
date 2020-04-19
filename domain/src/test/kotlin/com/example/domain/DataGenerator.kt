package com.example.domain

import com.example.domain.model.*

fun review(
    name: String = "test_review",
    sections: List<Section> = listOf()
) = Review(
    ReviewName(name),
    sections = sections
)

fun section(
    label: String = "test_section",
    mark: Int = 2
) = Section(
    SectionLabel(label),
    Mark(mark)
)