package fr.rafoufoun.review.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

@Composable
fun ViewModelDebug(vm: ViewModel) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(Color(0x000000))
            .alpha(0.5f),
        contentAlignment = Alignment.TopEnd
    ) {
        Text(
            text = "vm : $vm",
            fontSize = 12.sp
        )
    }
}