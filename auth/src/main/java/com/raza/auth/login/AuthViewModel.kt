package com.raza.auth.login

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.raza.auth.bean.FacebookLoginRequest
import com.raza.auth.bean.FacebookLoginResponse
import com.raza.auth.bean.ForgotPasswordRequest
import com.raza.auth.bean.GoogleSignInRequest
import com.raza.auth.bean.LoginRequest
import com.raza.auth.bean.LoginResponse
import com.raza.auth.bean.RegisterRequest
import com.raza.auth.bean.RequestType
import com.raza.auth.bean.ResetPasswordRequest
import com.raza.auth.bean.Result
import com.raza.logger.Logger
import com.raza.auth.networking.callForgotPasswordApi
import com.raza.auth.networking.callResetPasswordApi
import com.raza.auth.networking.makeGoogleSignInCall
import com.raza.auth.networking.makeLoginCall
import com.raza.auth.networking.makeNetworkRequest
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

                    when(response) {
                        is Result.Success -> {
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

                when(response) {
                    is Result.Success -> {
                        onRegisterSuccess()
                    }
                    is Result.Failure -> {
                        events.emit(UiEvent.ShowSnackbar(
                            response.error!!
                        ))
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

    fun forgotPassword(onResetPassword: (String) -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                val request = ForgotPasswordRequest()
                request.email = email
                val response = withContext(Dispatchers.IO) {
                    callForgotPasswordApi(request)
                }

                when(response) {
                    is Result.Success -> {
                        onResetPassword(response.data?.resetLink!!)
                    }
                    is Result.Failure -> {
                        events.emit(UiEvent.ShowSnackbar(
                            response.error!!
                        ))
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

    fun resetPassword(
        token: String?,
        onResetSuccess: () -> Unit
        ) {
        viewModelScope.launch {
            try {
                isLoading = true

                val request = ResetPasswordRequest()
                request.newPassword = password
                request.token = token

                val response = withContext(Dispatchers.IO) {
                    callResetPasswordApi(request)
                }

                when(response) {
                    is Result.Success -> {
                        onResetSuccess()
                    }
                    is Result.Failure -> {
                        events.emit(UiEvent.ShowSnackbar(
                            response.error!!
                        ))
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

    fun loginWithGoogle(data: Intent?,
                        onLogin: () -> Unit) {
        viewModelScope.launch {
            try {

                isLoading = true

                val task = GoogleSignIn.getSignedInAccountFromIntent(data!!)

                val account = task.getResult(
                    ApiException::class.java
                )
                account.idToken?.let {
                    val request = GoogleSignInRequest()
                    request.idToken = it

                    val response = withContext(Dispatchers.IO) {
                        makeGoogleSignInCall(request)
                    }

                    when(response) {
                        is Result.Success -> {
                            onLogin()
                        }
                        is Result.Failure -> {
                            events.emit(UiEvent.ShowSnackbar(
                                response.error!!
                            ))
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

    fun makeFacebookLoginCall(request: FacebookLoginRequest): Result<FacebookLoginResponse> {
        return makeNetworkRequest(FACEBOOK_AUTH_URL,
            request,
            RequestType.POST,
            FacebookLoginResponse::class.java
            )
    }
}