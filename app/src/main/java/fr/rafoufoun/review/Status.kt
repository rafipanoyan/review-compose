package fr.rafoufoun.review

import androidx.compose.Model
import com.example.domain.model.ReviewName
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

sealed class Screen {
    object Home : Screen()
    data class ReviewDetail(val name: ReviewName) : Screen()
    object NewReview : Screen()
}

@Model
object ReviewStatus {
    var previousScreens: Deque<Screen> = ConcurrentLinkedDeque()
    var currentScreen: Screen = Screen.Home
}

fun pushScreen(newScreen: Screen) {
    ReviewStatus.previousScreens.addFirst(ReviewStatus.currentScreen)
    ReviewStatus.currentScreen = newScreen
}

fun navigateTo(screen: Screen) {
    ReviewStatus.previousScreens.clear()
    ReviewStatus.currentScreen = screen
}

fun backPress(): Boolean =
    ReviewStatus.previousScreens.pollFirst()?.let { screen ->
        ReviewStatus.currentScreen = screen
        return@let true
    } ?: false