package com.raza.auth.networking

import com.google.gson.Gson
import com.raza.auth.bean.*
import com.raza.auth.logger.Logger
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
        ForgotPasswordResponse? {
    var response: ForgotPasswordResponse? = null

    val stringResponse = makeNetworkRequest(FORGOT_PASSWORD_URL,
        request, RequestType.POST)

    Logger.w(stringResponse)

    response = Gson().fromJson(stringResponse,
        ForgotPasswordResponse::class.java)

    return response
}

fun callResetPasswordApi(request: ResetPasswordRequest):
        ResetPasswordResponse? {

    var response: ResetPasswordResponse? = null

    val stringResponse = makeNetworkRequest(
        RESET_PASSWORD_URL, request, RequestType.POST)

    Logger.w(stringResponse)

    response = Gson().fromJson(stringResponse,
        ResetPasswordResponse::class.java)

    return response
}

fun makeLoginCall(request: LoginRequest): LoginResponse? {
    var response: LoginResponse? = null

    val stringResponse = makeNetworkRequest(LOGIN_URL, request, RequestType.POST)

    Logger.w(stringResponse, DEBUG_TAG)

    response = Gson().fromJson(stringResponse,
        LoginResponse::class.java)

    return response
}

fun makeRegisterCall(request: RegisterRequest): RegisterResponse? {
    var response: RegisterResponse? = null

    val stringResponse = makeNetworkRequest(REGISTER_URL, request, RequestType.POST)

    Logger.w(stringResponse, DEBUG_TAG)

    response = Gson().fromJson(stringResponse,
        RegisterResponse::class.java)

    return response
}

fun makeGoogleSignInCall(request: GoogleSignInRequest): GoogleSignInResponse {
    var response: GoogleSignInResponse? = null

    val stringResponse = makeNetworkRequest(GOOGLE_AUTH_URL,
        request,
        RequestType.POST)

    Logger.w(stringResponse, DEBUG_TAG)

    response = Gson().fromJson(stringResponse,
        GoogleSignInResponse::class.java)

    return response
}

fun makeNetworkRequest(url: String, request: Request, requestType: RequestType): String? {
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
        return error // Return error and success type based
        //convert to response model using type param
    }

    val response = readNetworkStream(connection.getInputStream())


    connection.disconnect()

    return response
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