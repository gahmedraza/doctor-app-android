package com.raza.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raza.auth.bean.LoginRequest
import com.raza.auth.bean.LoginResponse
import com.raza.auth.bean.RegisterRequest
import com.raza.auth.logger.Logger
import com.raza.auth.networking.makeLoginCall
import com.raza.auth.networking.makeRegisterCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val DEBUG_TAG = "Networking"

class AuthViewModel : ViewModel() {
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

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
    }

    fun login(onLoginSuccess: (LoginResponse) -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                var validation = validateUsername(username)
                showUsernameError = validation.showError
                usernameErrorMessage = validation.errorMessage

                validation = validatePassword(password)
                showPasswordError = validation.showError
                passwordErrorMessage = validation.errorMessage

                if (!showUsernameError && !showPasswordError) {
                    val response = withContext(Dispatchers.IO) {
                        val request = LoginRequest()
                        request.email = username
                        request.password = password
                        makeLoginCall(request)
                    }

                    response?.let {
                        events.emit(UiEvent.ShowSnackbar("response: $response"))
                        onLoginSuccess(response)
                    }
                }

            } catch (e: Exception) {
                Logger.w("${e.printStackTrace()}")
                events.emit(UiEvent.ShowSnackbar("${e.message}"))

            } finally {
                isLoading = false
            }
        }
    }

    fun register(onRegisterSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                //
                val request = RegisterRequest()
                request.username = username
                request.email = email
                request.password = password
                val response =
                    withContext(Dispatchers.IO) {
                        makeRegisterCall(request)
                    }

                response?.let {
                    events.emit(UiEvent.ShowSnackbar("response: $response"))
                    onRegisterSuccess()
                }

            } catch (e: Exception) {
                Logger.w("${e.printStackTrace()}")
                events.emit(UiEvent.ShowSnackbar("${e.message}"))

            } finally {
                isLoading = false
            }
        }
    }
}