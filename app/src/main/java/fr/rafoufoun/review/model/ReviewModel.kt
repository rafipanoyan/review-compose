package fr.rafoufoun.review.model

import com.example.domain.model.Mark
import com.example.domain.model.ReviewName
import com.example.domain.model.SectionLabel

data class ReviewModel(
    val name: ReviewName,
    val sections: List<SectionModel>
)

data class SectionModel(
    val label: SectionLabel,
    val mark: Mark
)