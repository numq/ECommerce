package com.numq.fooddeliveryclient.features.auth

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthScreen(auth: AuthViewModel = hiltViewModel()) {

    val (isSignUp, setIsSignUp) = rememberSaveable {
        mutableStateOf(false)
    }

    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        val offset = maxHeight.div(3)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SingleAuthScreen(auth, isSignUp)
            ToggleAuthMode(isSignUp, setIsSignUp)
            Spacer(Modifier.height(offset))
        }
    }
}