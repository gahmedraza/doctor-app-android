package com.raza.networking

import com.google.gson.Gson
import com.raza.logger.Logger
import com.raza.networking.bean.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

const val DEBUG_TAG = "Network"

//todo remo
const val API_KEY = "reqres_1377da5d7b4e4620b44f573493bca1a9"

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

        return Result.Failure(error)
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