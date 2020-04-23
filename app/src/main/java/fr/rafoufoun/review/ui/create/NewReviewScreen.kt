package fr.rafoufoun.review.ui.create

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import fr.rafoufoun.review.R

@Composable
fun NewReviewScreen(scaffoldState: ScaffoldState = ScaffoldState()) {
    val newReview = NewReviewModel.new()

    Scaffold(
        scaffoldState = scaffoldState,
        topAppBar = {
            TopAppBar(title = { Text(text = "New Review") })
        },
        floatingActionButton = {
            if (newReview.isValid) {
                FloatingActionButton(
                    onClick = { },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(vectorResource(id = R.drawable.ic_check_24))
                }
            }
        },
        bodyContent = { CreateReviewForm(newReview) }
    )
}

@Composable
fun CreateReviewForm(newReview: NewReviewModel) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        FilledTextField(
            value = newReview.name,
            onValueChange = {
                newReview.name = it
                newReview.validate()
            },
            label = { Text(text = "name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        newReview.sections.forEach { section ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = section.label, style = MaterialTheme.typography.subtitle2)
                Slider(
                    value = section.mark.toFloat(),
                    onValueChange = {
                        section.mark = it.toInt()
                        newReview.validate()
                    },
                    steps = section.outOf - 1,
                    valueRange = 0f..section.outOf.toFloat()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}