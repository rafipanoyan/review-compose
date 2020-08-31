package fr.rafoufoun.review

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.domain.model.ReviewName
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

sealed class Screen {
    object Home : Screen()
    data class ReviewDetail(val name: ReviewName) : Screen()
    object NewReview : Screen()
}

object ReviewStatus {
    var previousScreens: Deque<Screen> = ConcurrentLinkedDeque()
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)
}

fun pushScreen(newScreen: Screen) {
    ReviewStatus.previousScreens.addFirst(ReviewStatus.currentScreen.value)
    ReviewStatus.currentScreen.value = newScreen
}

fun navigateTo(screen: Screen) {
    ReviewStatus.previousScreens.clear()
    ReviewStatus.currentScreen.value = screen
}

fun backPress(): Boolean =
    ReviewStatus.previousScreens.pollFirst()?.let { screen ->
        ReviewStatus.currentScreen.value = screen
        return@let true
    } ?: false