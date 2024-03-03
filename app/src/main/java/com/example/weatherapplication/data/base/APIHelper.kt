package com.example.weatherapplication.data.base

import retrofit2.Response
import com.example.weatherapplication.domain.base.Error

object APIHelper {
    const val API_VERSION = "BuildConfig.API_VERSION"
    const val APP_ID = "e3baf13f132decb60c5d8e28a73af8f2"

    fun toResponseError(tr: Throwable?): Error.ResponseError {
        return Error.ResponseError(code = 0, message = "", tr = tr)
    }

    fun <T> toResponseError(response: Response<T>): Error.ResponseError {
        return Error.ResponseError(
            code = response.code(), message = response.message(), tr = null,
            data = mapOf(
                Error.DATA_URL to response.raw().request.url
            )
        )
    }

    fun toOTPError(tr: Throwable?): Error.OTPError {
        return Error.OTPError(code = 0, message = "", tr = tr)
    }

    fun <T> toOTPError(response: Response<T>): Error.OTPError {
        return Error.OTPError(code = response.code(), message = response.message(), tr = null)
    }

    fun toIncorrectOTP(tr: Throwable?): Error.IncorrectOTP {
        return Error.IncorrectOTP(code = 0, message = "", tr = tr)
    }

    fun <T> toIncorrectOTP(response: Response<T>): Error.IncorrectOTP {
        return Error.IncorrectOTP(code = response.code(), message = response.message(), tr = null)
    }

    fun toCheckExistingUser(tr: Throwable?): Error.CheckExistingUser {
        return Error.CheckExistingUser(code = 0, message = "", tr = tr)
    }

    fun <T> toCheckExistingUser(response: Response<T>): Error.CheckExistingUser {
        return Error.CheckExistingUser(code = response.code(), message = response.message(), tr = null)
    }
}