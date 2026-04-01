package com.raza.auth.register

import androidx.lifecycle.viewModelScope
import com.raza.auth.common.BaseViewModel
import com.raza.auth.bean.RegisterRequest
import com.raza.auth.bean.UiEvent
import com.raza.auth.networking.makeRegisterCall
import com.raza.logger.Logger
import com.raza.networking.bean.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel : BaseViewModel() {

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
}