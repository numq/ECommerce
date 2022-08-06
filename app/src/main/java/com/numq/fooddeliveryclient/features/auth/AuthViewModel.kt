package com.numq.fooddeliveryclient.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    fun signIn(login: String, password: String) {
        TODO()
    }

    fun signUp(login: String, password: String) {
        TODO()
    }

    fun cancelAuth() = viewModelScope.cancel()

}