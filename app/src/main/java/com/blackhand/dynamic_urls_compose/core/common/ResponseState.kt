package com.blackhand.dynamic_urls_compose.core.common

sealed class ResponseState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResponseState<T>(data = data)
    class Error<T>(error: String?, data: T? = null) : ResponseState<T>(message = error, data = data)
    class Loading<T>() : ResponseState<T>()
}