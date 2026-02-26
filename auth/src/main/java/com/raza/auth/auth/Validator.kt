package com.raza.auth.auth

class Validator {
    var showError: Boolean = false
    var errorMessage: String = ""
}

fun validateUsername(username: String): Validator {
    val userValidator = Validator()

    userValidator.apply {
        if (username.isEmpty()) {
            showError = true
            errorMessage = "Username cannot be empty"
        } else if (username.length < 5) {
            showError = true
            errorMessage = "Username cannot be less than 5 characters"
        } else {
            showError = false
        }
    }

    return userValidator
}

fun validatePassword(password: String): Validator {
    val validator = Validator()

    validator.apply {
        if (password.isEmpty()) {
            showError = true
            errorMessage = "Password cannot be empty"
        } else if (password.length < 5) {
            showError = true
            errorMessage = "Password cannot be less than 5 characters"
        } else {
            showError = false
        }
    }

    return validator
}