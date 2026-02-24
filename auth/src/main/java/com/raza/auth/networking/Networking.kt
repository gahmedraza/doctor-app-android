package com.raza.auth.networking

import com.google.gson.Gson
import com.raza.auth.bean.*
import com.raza.logger.Logger
import com.raza.auth.login.API_KEY
import com.raza.auth.login.DEBUG_TAG
import com.raza.auth.login.FORGOT_PASSWORD_URL
import com.raza.auth.login.GOOGLE_AUTH_URL
import com.raza.auth.login.LOGIN_URL
import com.raza.auth.login.REGISTER_URL
import com.raza.auth.login.RESET_PASSWORD_URL
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

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

fun <T> makeNetworkRequest(url: String,
                           request: Request,
                           requestType: RequestType,
                           responseType: Class<T>): Result<T> {
    val url = URL(url)
    val connection = url.openConnection() as HttpURLConnection

    connection.requestMethod = requestType.value
    connection.connectTimeout = 10 * 1000 //10s
    connection.readTimeout = 10 * 1000 //10s

    connection.addRequestProperty(HeaderKeys.API_KEY.value, API_KEY)

    if (requestType == RequestType.POST) {
        connection.doOutput = true
        connection.setRequestProperty("Content-Type",
            "application/json")

        val json = Gson().toJson(request)
        val outputStream = connection.getOutputStream()
        outputStream.write(json.toByteArray())
        outputStream.flush()
        outputStream.close()
    }

    val responseCode = connection.responseCode
    if (responseCode > HttpURLConnection.HTTP_PARTIAL) {
        val error = readNetworkStream(connection.errorStream)

        Logger.w(error)

        return Result.Failure(error) // Return error and success type based
        //convert to response model using type param
    }

    val response = readNetworkStream(connection.getInputStream())

    Logger.w(response, DEBUG_TAG)

    val result: T = Gson().fromJson(response, responseType)

    connection.disconnect()

    return Result.Success(result)
}

fun readNetworkStream(stream: InputStream): String {
    val reader = BufferedReader(InputStreamReader(stream))
    val response = StringBuilder()
    var line: String?

    while (reader.readLine().also { line = it } != null) {
        response.append(line)
    }

    reader.close()

    return response.toString()
}