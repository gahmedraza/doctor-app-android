package com.raza.networking.bean

enum class RequestType(val value: String) {
    GET("GET"),
    POST("POST")
}

enum class HeaderKeys(val value: String) {
    API_KEY("x-api-key")
}

interface Request {

}

sealed class Result<out T> {
    data class Failure(val error: String?): Result<Nothing>()
    data class Success<out T>(val data: T?): Result<T>()
}