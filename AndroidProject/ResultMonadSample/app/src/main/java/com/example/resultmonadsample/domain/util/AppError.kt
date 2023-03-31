package com.example.resultmonadsample.domain.util

sealed class AppError {
    data class UnknownError(val error: Exception) : AppError()
    object OtherError: AppError()
}
