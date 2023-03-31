package com.example.resultmonadsample.ui.uistate

import com.example.resultmonadsample.domain.util.AppError

enum class ErrorDialogType {
    NormalError, Unknown, None;
    companion object {
        fun fromAppError(appError: AppError): ErrorDialogType {
            return when(appError) {
                AppError.OtherError -> NormalError
                is AppError.UnknownError -> Unknown
            }
        }
    }
}

sealed class CoreUiState(
    open val isLoading: Boolean = true,
    open val isErrorDialogShown: Boolean = false,
    open val errorDialogType: ErrorDialogType = ErrorDialogType.None
)

data class SampleScreenUiState(
    val name : String = "",
    val note: String = "",
    override val isLoading: Boolean = true,
    override val isErrorDialogShown: Boolean = false,
    override val errorDialogType: ErrorDialogType = ErrorDialogType.None,
): CoreUiState(isLoading, isErrorDialogShown, errorDialogType)
