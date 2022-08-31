package br.com.network.entity

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(
        val message: String,
        val throwable: Throwable = Throwable(),
        val code: String? = null,
        val type: ErrorType = ErrorType.Unknown
    ) : Result<Nothing>()
}