package fr.rafoufoun.review.ui.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import fr.rafoufoun.review.R
import fr.rafoufoun.review.ReviewApplication

@Composable
fun NewReviewScreen(navController: NavHostController, scaffoldState: ScaffoldState) {
    val reviewFormVm = viewModel<ReviewFormViewModel>(
        factory = ReviewFormViewModel.Factory(ReviewApplication.get().reviewSource.createReview)
    )
    val formState = reviewFormVm.formResult.observeAsState().value

    val newReview = remember { reviewFormVm.newReview }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            NewReviewTopAppBar(navController)
        },
        floatingActionButton = {
            if (newReview.isValid) {
                NewReviewDoneFab(
                    navController = navController,
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
fun NewReviewTopAppBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "New Review") },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(vectorResource(id = R.drawable.ic_baseline_arrow_back_24))
            }
        }
    )
}

@Composable
fun NewReviewDoneFab(
    navController: NavController,
    reviewFormVm: ReviewFormViewModel,
    newReview: NewReviewModel
) {
    FloatingActionButton(
        onClick = {
            reviewFormVm.createReview(newReview) {
                navController.popBackStack()
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
            Text(text = "DUPLICATE REVIEW")
            Spacer(modifier = Modifier.height(16.dp))
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