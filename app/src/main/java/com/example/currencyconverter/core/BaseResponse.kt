package com.example.currencyconverter.core

sealed class BaseResponse {
    class Success(val data: Any) : BaseResponse()
    class Error(val errorKind: NetworkErrorsHelper.ErrorKind, val message: String? = "") : BaseResponse()
    class Loading(val isLoading: Boolean) : BaseResponse()
}