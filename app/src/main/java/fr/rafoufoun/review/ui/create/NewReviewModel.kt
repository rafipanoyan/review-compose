package fr.rafoufoun.review.ui.create

import androidx.compose.Model

@Model
class NewReviewModel private constructor(var name: String, val sections: List<NewSectionModel>) {

    var isValid: Boolean = false
        private set

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

@Model
class NewSectionModel(
    val label: String,
    var mark: Int,
    val outOf: Int
)

fun newSections() = listOf(
    NewSectionModel("Story", 0, 10),
    NewSectionModel("Characters", 0, 5)
)