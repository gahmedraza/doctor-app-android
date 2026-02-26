package com.raza.auth.login

class LoginUiState {
    var username: String? = null
    var password: String? = null
    var usernameError: String? = null
    var passwordError: String? = null
    var isLoading: Boolean? = false
    var loginSuccess: Boolean = false
    var errorMessage: String? = null
}