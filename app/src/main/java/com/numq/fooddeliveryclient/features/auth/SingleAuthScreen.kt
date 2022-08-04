package com.numq.fooddeliveryclient.features.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SingleAuthScreen(
    auth: AuthViewModel,
    isSignUp: Boolean,
    validator: InputValidator = InputValidator
) {
    val emptyString = ""
    val (email, setEmail) = rememberSaveable {
        mutableStateOf(emptyString)
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf(emptyString)
    }
    val (confirmedPassword, setConfirmedPassword) = rememberSaveable {
        mutableStateOf(emptyString)
    }
    val (fieldsAreFilled, setFieldsAreFilled) = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(isSignUp) {
        setConfirmedPassword(emptyString)
    }

    LaunchedEffect(email, password, confirmedPassword) {
        val emailFilled = email.isNotBlank() && (email.length in validator.emailConstraints)
        val passwordFilled =
            password.isNotBlank() && (password.length in validator.passwordConstraints)
        if (isSignUp) {
            val confirmedPasswordFilled =
                confirmedPassword.isNotBlank() && (confirmedPassword.length in validator.passwordConstraints)
            setFieldsAreFilled(emailFilled && passwordFilled && confirmedPasswordFilled)
        } else {
            setFieldsAreFilled(emailFilled && passwordFilled)
        }
    }

    Box(Modifier.padding(8.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EmailField(
                placeholder = "Email",
                maxLength = validator.emailConstraints.last,
                email,
                setEmail,
                validator.validateString
            )
            PasswordField(
                placeholder = "Password",
                maxLength = validator.passwordConstraints.last,
                password,
                setPassword,
                validator.validateString
            )
            AnimatedVisibility(visible = isSignUp) {
                PasswordField(
                    placeholder = "Confirmed password",
                    maxLength = validator.passwordConstraints.last,
                    confirmedPassword,
                    setConfirmedPassword,
                    validator.validateString
                )
            }
            IconButton(onClick = {
                if (isSignUp) auth.signUp(email, password, confirmedPassword)
                else auth.signIn(email, password)
            }, enabled = fieldsAreFilled) {
                Icon(Icons.Rounded.Done, "Confirm icon")
            }
        }
    }
}