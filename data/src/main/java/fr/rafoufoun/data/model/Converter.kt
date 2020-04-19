package fr.rafoufoun.data.model

import com.example.domain.model.*

fun ReviewAndSections.toReview(): Review =
    Review(
        ReviewName(review.name),
        sections.map(SectionDB::toSection)
    )

fun SectionDB.toSection(): Section =
    Section(
        SectionLabel(label),
        Mark(mark)
    )