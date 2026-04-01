package com.raza.auth.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.raza.auth.bean.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow

open class BaseViewModel : ViewModel() {
    var username by mutableStateOf("")

    var password by mutableStateOf("")

    var showUsernameError by mutableStateOf(false)

    var usernameErrorMessage by mutableStateOf("")

    var showPasswordError by mutableStateOf(false)

    var passwordErrorMessage by mutableStateOf("")

    var email by mutableStateOf("")

    var confirmPassword by mutableStateOf("")

    var events = MutableSharedFlow<UiEvent>()

    var isLoading by mutableStateOf(false)
}