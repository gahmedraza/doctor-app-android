package com.raza.auth.login

import androidx.lifecycle.viewModelScope
import com.raza.auth.common.BaseViewModel
import com.raza.auth.bean.FacebookLoginRequest
import com.raza.auth.bean.LoginRequest
import com.raza.auth.bean.LoginResponse
import com.raza.auth.bean.UiEvent
import com.raza.auth.networking.makeFacebookLoginCall
import com.raza.auth.networking.makeLoginCall
import com.raza.logger.Logger
import com.raza.networking.bean.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel() {

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

                    when(response) {
                        is com.raza.networking.bean.Result.Success -> {
                            onLoginSuccess(response.data!!)
                        }
                        is Result.Failure -> {
                            events.emit(UiEvent.ShowSnackbar(response.error!!))
                        }
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

    fun loginWithFacebook(token: String?, onFacebookLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                val request = FacebookLoginRequest()
                request.accessToken = token

                val response = withContext(Dispatchers.IO) {
                    makeFacebookLoginCall(request)
                }

                when(response) {
                    is Result.Success -> {
                        onFacebookLoginSuccess()
                    }
                    is Result.Failure -> {

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
}