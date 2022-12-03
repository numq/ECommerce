package com.numq.fooddeliveryclient.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.numq.fooddeliveryclient.keyboard.NumeralKeyboard

@Composable
fun AuthScreen(vm: AuthViewModel = hiltViewModel(), navigateToCatalog: (String) -> Unit) {

    val state by vm.state.collectAsState()

    state.token?.let(navigateToCatalog) ?: run {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (state.isLoading) Arrangement.SpaceBetween else Arrangement.Bottom
        ) {
            if (state.isLoading) {
                LinearProgressIndicator(Modifier.fillMaxWidth())
            }
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Bottom)
            ) {
                if (state.isAuthenticationInProgress) {
                    val confirmationCodeMaxLength = 4
                    val (confirmationCodeValue, setConfirmationCodeValue) = remember {
                        mutableStateOf("")
                    }
                    Text("Type confirmation code:", fontSize = 16.sp)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(confirmationCodeValue, fontSize = 24.sp)
                    }
                    Divider()
                    NumeralKeyboard(
                        onClick = {
                            if (confirmationCodeValue.length <= confirmationCodeMaxLength) {
                                setConfirmationCodeValue(confirmationCodeValue + it)
                            }
                        }, onBackspace = {
                            setConfirmationCodeValue(confirmationCodeValue.dropLast(1))
                        }
                    )
                    LaunchedEffect(confirmationCodeValue) {
                        if (confirmationCodeValue.length == confirmationCodeMaxLength) {
                            vm.sendConfirmationCode(confirmationCodeValue)
                        }
                    }
                } else {
                    val phoneNumberMaxLength = 10
                    val (phoneNumberValue, setPhoneNumberValue) = remember {
                        mutableStateOf("")
                    }
                    Text("Type phone number:", fontSize = 16.sp)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(phoneNumberValue, fontSize = 24.sp)
                    }
                    Divider()
                    NumeralKeyboard(
                        onClick = {
                            if (phoneNumberValue.length <= phoneNumberMaxLength) {
                                setPhoneNumberValue(phoneNumberValue + it)
                            }
                        },
                        onBackspace = {
                            setPhoneNumberValue(phoneNumberValue.dropLast(1))
                        }
                    )
                    LaunchedEffect(phoneNumberValue) {
                        if (phoneNumberValue.length == phoneNumberMaxLength) {
                            vm.sendPhoneNumber(phoneNumberValue)
                        }
                    }
                }
            }
        }
    }
}