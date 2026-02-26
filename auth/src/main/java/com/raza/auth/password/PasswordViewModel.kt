package com.raza.auth.password

import androidx.lifecycle.viewModelScope
import com.raza.auth.common.BaseViewModel
import com.raza.auth.bean.ForgotPasswordRequest
import com.raza.auth.bean.ResetPasswordRequest
import com.raza.auth.bean.UiEvent
import com.raza.auth.networking.callForgotPasswordApi
import com.raza.auth.networking.callResetPasswordApi
import com.raza.logger.Logger
import com.raza.networking.bean.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PasswordViewModel : BaseViewModel() {
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
                        events.emit(
                            UiEvent.ShowSnackbar(
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

    fun resetPassword(token: String?, onResetSuccess: () -> Unit) {
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
}