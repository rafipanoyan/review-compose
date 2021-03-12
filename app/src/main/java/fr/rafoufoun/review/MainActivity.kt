package fr.rafoufoun.review

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.rafoufoun.review.ui.ReviewApp

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ReviewApp()
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        ReviewApp()
    }
}