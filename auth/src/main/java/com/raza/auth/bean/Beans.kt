package com.raza.auth.bean

import com.raza.networking.bean.Request


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

class FacebookLoginRequest: Request {
    var accessToken: String? = null
}

class FacebookLoginResponse{
    var token: String? = null

    var id: String? = null
}

class GithubLoginRequest: Request {
    var code: String? = null
}

class GithubLoginResponse {

    var token: String? = null

    var userId: String? = null
}