package fr.rafoufoun.review.ui.create

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import fr.rafoufoun.review.R
import fr.rafoufoun.review.ReviewFormViewModelAmbient
import fr.rafoufoun.review.backPress

@Composable
fun NewReviewScreen() {
    val reviewFormVm = ReviewFormViewModelAmbient.current
    val formState = reviewFormVm.formResult.observeAsState()
    val formStateValue = formState.value
    if (formStateValue == ReviewFormResult.Success) {
        backPress()
    }

    val newReview = remember { NewReviewModel.new() }
    Scaffold(
        topBar = {
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
        bodyContent = { CreateReviewForm(newReview, formStateValue) }
    )
}

@Composable
fun CreateReviewForm(newReview: NewReviewModel, formState: ReviewFormResult?) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

        if (formState == ReviewFormResult.DuplicateReviewError) {
            Text(text = "DUPLICATE REVIEW")
            Spacer(modifier = Modifier.height(16.dp))
        }

        OutlinedTextField(
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