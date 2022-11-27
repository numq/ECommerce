package com.numq.fooddeliveryclient.error

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch

@Composable
fun ShowError(
    scaffoldState: ScaffoldState,
    exception: Exception
) {
    LaunchedEffect(Unit) {
        launch {
            scaffoldState.snackbarHostState.showSnackbar(
                exception.localizedMessage ?: exception.javaClass.simpleName.toString(),
                duration = SnackbarDuration.Short
            )
        }
    }
}