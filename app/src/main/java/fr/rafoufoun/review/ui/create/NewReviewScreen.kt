package fr.rafoufoun.review.ui.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import fr.rafoufoun.review.R
import fr.rafoufoun.review.ReviewApplication

@Composable
fun NewReviewScreen(scaffoldState: ScaffoldState, onBack: () -> Unit) {
    val reviewFormVm = viewModel<ReviewFormViewModel>(
        factory = ReviewFormViewModel.Factory(ReviewApplication.get().reviewSource.createReview)
    )
    val formState = reviewFormVm.formResult.observeAsState().value

    val newReview = remember { reviewFormVm.newReview }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewReviewTopAppBar(onBack)
        },
        floatingActionButton = {
            if (newReview.isValid) {
                NewReviewDoneFab(
                    onBack = onBack,
                    reviewFormVm = reviewFormVm,
                    newReview = newReview
                )
            }
        }
    ) {
        CreateReviewForm(newReview, formState)
    }

}

@Composable
fun NewReviewTopAppBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = "New Review") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(vectorResource(id = R.drawable.ic_baseline_arrow_back_24))
            }
        }
    )
}

@Composable
fun NewReviewDoneFab(
    onBack: () -> Unit,
    reviewFormVm: ReviewFormViewModel,
    newReview: NewReviewModel
) {
    FloatingActionButton(
        onClick = {
            reviewFormVm.createReview(newReview) {
                onBack()
            }
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(vectorResource(id = R.drawable.ic_check_24))
    }
}

@Composable
fun CreateReviewForm(newReview: NewReviewModel, formState: ReviewFormResult?) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

        if (formState == ReviewFormResult.DuplicateReviewError) {
            NewReviewDuplicateWarning()
        }

        NewReviewName(newReview = newReview)
        Spacer(modifier = Modifier.height(16.dp))

        newReview.sections.forEach { section ->
            NewReviewSection(
                section = section,
                onValueChanged = {
                    newReview.validate()
                }
            )
        }
    }
}

@Composable
fun NewReviewDuplicateWarning() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(fr.rafoufoun.review.error)
                .padding(16.dp)
        ) {
            Text(text = "DUPLICATE REVIEW")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun NewReviewName(newReview: NewReviewModel) {
    OutlinedTextField(
        value = newReview.name,
        onValueChange = {
            newReview.name = it
            newReview.validate()
        },
        label = { Text(text = "name") },
        modifier = Modifier.fillMaxWidth(),
        onImeActionPerformed = { action, softwareController ->
            if (action == ImeAction.Done) {
                softwareController?.hideSoftwareKeyboard()
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

@Composable
fun NewReviewSection(
    section: NewSectionModel,
    onValueChanged: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = section.label, style = MaterialTheme.typography.subtitle2)
        Slider(
            value = section.mark.toFloat(),
            onValueChange = {
                section.mark = it.toInt()
                onValueChanged()
            },
            steps = section.outOf - 1,
            valueRange = 0f..section.outOf.toFloat()
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
fun NewReviewScreenPreview() {
    NewReviewScreen(scaffoldState = rememberScaffoldState(), onBack = {})
}

@Composable
@Preview
fun NewReviewDuplicateWarningPreview() {
    NewReviewDuplicateWarning()
}