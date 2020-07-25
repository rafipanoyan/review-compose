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
        Mark(mark, outOf)
    )

internal fun Review.toDB() =
    ReviewDB(this.name.value)

internal fun Section.toDB(reviewName: ReviewName) =
    SectionDB(
        reviewName = reviewName.value,
        label = label.value,
        mark = mark.value,
        outOf = mark.outOf
    )