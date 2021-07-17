package com.example.currencyconverter.utils

object NetworkErrorsHelper {
    fun getErrorKind(statusCode: Int): ErrorKind {
        return when (statusCode) {
            in 500..599 -> ErrorKind.SERVER_DOWN
            408 -> ErrorKind.TIME_OUT
            401 -> ErrorKind.UNAUTHORIZED
            else -> ErrorKind.UNEXPECTED
        }
    }

    enum class ErrorKind {
        SERVER_DOWN,
        TIME_OUT,
        UNAUTHORIZED,
        UNEXPECTED
    }

}