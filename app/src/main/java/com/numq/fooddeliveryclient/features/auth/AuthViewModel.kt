package com.numq.fooddeliveryclient.features.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    fun signIn(login: String, password: String) {
        TODO()
    }

    fun signUp(login: String, password: String, confirmedPassword: String) {
        TODO()
    }

}