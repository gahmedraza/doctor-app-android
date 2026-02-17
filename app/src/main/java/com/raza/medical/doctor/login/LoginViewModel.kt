package com.raza.medical.doctor.login

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

const val BASE_URL = "https://reqres.in/api"
const val AUTH = "/users"
const val LOGIN_URL = BASE_URL + AUTH

class LoginViewModel : ViewModel() {

}

suspend fun makeLoginCall(request: LoginRequest): LoginResponse? {
    var response: LoginResponse? = null

    val stringResponse =
        makeGetRequest(
            LOGIN_URL,
            request,
            RequestType.POST
        )

    return response
}

enum class RequestType(name: String) {
    GET("GET"),
    POST("POST")
}

fun makeGetRequest(
    url: String,
    request: LoginRequest,
    requestType: RequestType
): String {
    val url = URL(url)
    val connection = url.openConnection() as HttpURLConnection

    connection.requestMethod = requestType.name
    connection.connectTimeout = 10 * 1000 //10s
    connection.readTimeout = 10 * 1000 //10s

    connection.addRequestProperty("x-api-key", "reqres_1377da5d7b4e4620b44f573493bca1a9")

    if (requestType == RequestType.POST) {
        val json = Gson().toJson(request)
        val outputStream = connection.getOutputStream()
        outputStream.write(json.toByteArray())
    }

    val responseCode = connection.responseCode
    if (responseCode != HttpURLConnection.HTTP_OK) {
        throw Exception("Http error code: $responseCode")
    }

    val reader = BufferedReader(InputStreamReader(connection.getInputStream()))
    val response = StringBuilder()
    var line: String?

    while (reader.readLine().also { line = it } != null) {
        response.append(line)
    }

    reader.close()
    connection.disconnect()

    return response.toString()
}


class LoginRequest {
    var username: String? = null
    var password: String? = null
}

class LoginResponse {
    var userId: String? = null
    var sessionToken: String? = null
}