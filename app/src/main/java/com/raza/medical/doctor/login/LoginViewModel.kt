package com.raza.medical.doctor.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.raza.medical.doctor.logger.Logger
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

const val DEBUG_TAG = "Networking"
const val API_KEY = "reqres_1377da5d7b4e4620b44f573493bca1a9"
const val BASE_URL = "https://reqres.in/api"
const val LOGIN = "/register"

const val SIGNUP = "/auth/register"
const val LOGIN_URL = BASE_URL + LOGIN

const val SIGNUP_URL = BASE_URL + SIGNUP

class LoginViewModel : ViewModel() {

}

fun makeLoginCall(request: LoginRequest): LoginResponse? {
    var response: LoginResponse? = null

    val stringResponse = makeNetworkRequest(LOGIN_URL, request, RequestType.POST)

    Logger.d(stringResponse, DEBUG_TAG)

    response = Gson().fromJson(stringResponse,
        LoginResponse::class.java)

    return response
}

fun makeRegisterCall(request: SignupRequest): SignupResponse? {
    var response: SignupResponse? = null

    val stringResponse = makeNetworkRequest(SIGNUP_URL, request, RequestType.POST)

    Logger.d(stringResponse, DEBUG_TAG)

    response = Gson().fromJson(stringResponse,
        SignupResponse::class.java)

    return response
}

enum class RequestType(val value: String) {
    GET("GET"),
    POST("POST")
}

enum class HeaderKeys(val value: String) {
    API_KEY("x-api-key")
}

fun makeNetworkRequest(url: String, request: Request, requestType: RequestType): String {
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

        Logger.d(error)
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


class LoginRequest: Request {
    //var username: String? = null

    var email: String? = null
    var password: String? = null
}

class LoginResponse {
    var userId: String? = null
    var sessionToken: String? = null
}

class SignupRequest: Request {
    var username: String? = null
    var email: String? = null
    var password: String? = null
}

class SignupResponse {
    var userId: String? = null
    var sessionToken: String? = null
}

interface Request {

}