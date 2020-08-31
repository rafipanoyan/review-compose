package fr.rafoufoun.review.ui.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.domain.model.*

class NewReviewModel private constructor(name: String, val sections: List<NewSectionModel>) {

    var name by mutableStateOf(name)
    var isValid by mutableStateOf(false)

    fun validate() {
        isValid =
            name.isNotEmpty() &&
                    sections.fold(true) { valid, newSectionModel ->
                        valid && newSectionModel.mark > 0
                    }
    }

    companion object {
        fun new() = NewReviewModel("", newSections())
    }
}

class NewSectionModel(
    val label: String,
    mark: Int,
    val outOf: Int
) {
    var mark by mutableStateOf(mark)
}

fun newSections() = listOf(
    NewSectionModel("Story", 0, 10),
    NewSectionModel("Characters", 0, 5)
)

fun NewReviewModel.toReview(): Review =
    Review(
        ReviewName(name),
        sections.map { section ->
            Section(
                SectionLabel(section.label),
                Mark(section.mark, section.outOf)
            )
        }
    )