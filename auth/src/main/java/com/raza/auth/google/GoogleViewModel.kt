package com.raza.auth.google

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.raza.auth.common.BaseViewModel
import com.raza.auth.bean.GoogleSignInRequest
import com.raza.auth.bean.UiEvent
import com.raza.auth.networking.makeGoogleSignInCall
import com.raza.logger.Logger
import com.raza.networking.bean.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GoogleViewModel : BaseViewModel() {

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
                        is com.raza.networking.bean.Result.Success -> {
                            onLogin()
                        }
                        is Result.Failure -> {
                            events.emit(
                                UiEvent.ShowSnackbar(
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
}