package com.raza.auth.bean

enum class RequestType(val value: String) {
    GET("GET"),
    POST("POST")
}

enum class HeaderKeys(val value: String) {
    API_KEY("x-api-key")
}

interface Request {

}

class ForgotPasswordRequest: Request {
    var email: String? = null
}

class ForgotPasswordResponse {
    val resetLink: String? = null
}

class ResetPasswordRequest: Request {
    var newPassword: String? = null
    var token: String? = null
}

class ResetPasswordResponse {
    var result: Boolean? = false
    var message: String? = null
}

class LoginRequest: Request {
    //var username: String? = null

    var email: String? = null
    var password: String? = null
}

class LoginResponse {
    var userId: String? = null
    var sessionToken: String? = null
}

class RegisterRequest: Request {
    var username: String? = null
    var email: String? = null
    var password: String? = null
}

class RegisterResponse {
    var userId: String? = null
    var sessionToken: String? = null
}

class GoogleSignInRequest: Request {
    var idToken: String? = null
}

class GoogleSignInResponse {
    var userId: String? = null
    var accessToken: String? = null
    var refreshToken: String? = null
}

sealed class Result<out T> {
    data class Failure(val error: String?): Result<Nothing>()
    data class Success<out T>(val data: T?): Result<T>()
}

class FacebookLoginRequest: Request {
    var accessToken: String? = null
}

class FacebookLoginResponse{
    var token: String? = null

    var id: String? = null
}