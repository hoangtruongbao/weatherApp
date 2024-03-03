package com.example.weatherapplication.domain.base

sealed class Error(val code: Int, val message: String, val tr: Throwable?, val data: Map<String, Any>? = null) {
    class NetworkError(code: Int, message: String, tr: Throwable?) : Error(code, message, tr)
    class ResponseError(code: Int, message: String, tr: Throwable?, data: Map<String, Any>? = null) : Error(code, message, tr, data)
    class OTPError(code: Int, message: String, tr: Throwable?) : Error(code, message, tr)
    class CheckExistingUser(code: Int, message: String, tr: Throwable?) : Error(code, message, tr)
    class IncorrectOTP(code: Int, message: String, tr: Throwable?) : Error(code, message, tr)
    class UserIdEmpty(code: Int = 0, message: String = "UserId is empty", tr: Throwable? = null) : Error(code, message, tr)

    fun getUrl(): String {
        return (data?.get(DATA_URL)).toString()
    }

    fun getThrowable(): Throwable {
        return tr ?: Throwable("$code - $message")
    }

    override fun toString(): String {
        return "$code - $message"
    }

    companion object {
        const val DATA_URL = "url"
    }
}