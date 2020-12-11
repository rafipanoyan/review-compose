package fr.rafoufoun.review

object Screen {
    object Home {
        const val route = "home"
    }

    object NewReview {
        const val route = "new_review"
    }

    object Detail {
        const val route = "detail/{${Args.name}}"

        fun getRoute(reviewName: String): String = "detail/$reviewName"

        object Args {
            const val name = "reviewName"
        }
    }
}