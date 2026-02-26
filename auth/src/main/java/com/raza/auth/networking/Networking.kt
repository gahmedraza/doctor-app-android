package com.raza.auth.networking

import com.raza.auth.auth.FORGOT_PASSWORD_URL
import com.raza.auth.auth.GOOGLE_AUTH_URL
import com.raza.auth.auth.LOGIN_URL
import com.raza.auth.auth.REGISTER_URL
import com.raza.auth.auth.RESET_PASSWORD_URL
import com.raza.auth.bean.ForgotPasswordRequest
import com.raza.auth.bean.ForgotPasswordResponse
import com.raza.auth.bean.GoogleSignInRequest
import com.raza.auth.bean.GoogleSignInResponse
import com.raza.auth.bean.LoginRequest
import com.raza.auth.bean.LoginResponse
import com.raza.auth.bean.RegisterRequest
import com.raza.auth.bean.RegisterResponse
import com.raza.auth.bean.ResetPasswordRequest
import com.raza.auth.bean.ResetPasswordResponse
import com.raza.networking.bean.*
import com.raza.networking.makeNetworkRequest

fun callForgotPasswordApi(request: ForgotPasswordRequest):
        Result<ForgotPasswordResponse> {

    return makeNetworkRequest(FORGOT_PASSWORD_URL, request,
        RequestType.POST, ForgotPasswordResponse::class.java)
}

fun callResetPasswordApi(request: ResetPasswordRequest):
        Result<ResetPasswordResponse> {

    return makeNetworkRequest(
        RESET_PASSWORD_URL, request,
        RequestType.POST, ResetPasswordResponse::class.java)
}

fun makeLoginCall(request: LoginRequest): Result<LoginResponse> {
    return makeNetworkRequest(LOGIN_URL, request,
        RequestType.POST, LoginResponse::class.java)
}

fun makeRegisterCall(request: RegisterRequest): Result<RegisterResponse> {
    return makeNetworkRequest(REGISTER_URL, request,
        RequestType.POST, RegisterResponse::class.java)
}

fun makeGoogleSignInCall(request: GoogleSignInRequest):
        Result<GoogleSignInResponse> {
    return makeNetworkRequest(GOOGLE_AUTH_URL, request,
        RequestType.POST, GoogleSignInResponse::class.java)
}