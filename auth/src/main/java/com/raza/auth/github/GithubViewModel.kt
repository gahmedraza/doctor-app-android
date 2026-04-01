package com.raza.auth.github

import androidx.lifecycle.viewModelScope
import com.raza.auth.common.BaseViewModel
import com.raza.auth.bean.GithubLoginRequest
import com.raza.auth.bean.UiEvent
import com.raza.networking.bean.Result
import com.raza.logger.Logger
import com.raza.auth.networking.makeGithubLoginCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val DEBUG_TAG = "Networking"

class GithubViewModel : BaseViewModel() {
    fun loginWithGithub(token: String?,
                        onGithubSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true

                val request = GithubLoginRequest()
                request.code = token

                val response = withContext(
                    Dispatchers.IO
                ) {
                    makeGithubLoginCall(request)
                }

                when(response) {
                    is Result.Success -> {
                        onGithubSuccess()
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