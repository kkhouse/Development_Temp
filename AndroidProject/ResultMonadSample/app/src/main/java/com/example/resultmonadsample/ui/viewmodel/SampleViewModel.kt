package com.example.resultmonadsample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resultmonadsample.App
import com.example.resultmonadsample.ui.uistate.SampleScreenUiState
import com.example.resultmonadsample.usecase.SampleUseCase
import com.example.resultmonadsample.usecase.SampleUseCaseImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class SampleViewModel(
    private val useCase: SampleUseCase = SampleUseCaseImpl()
) : ViewModel() {

    private var _uiState: MutableStateFlow<SampleScreenUiState> = MutableStateFlow(SampleScreenUiState())
    val uiState: StateFlow<SampleScreenUiState> = _uiState

    init {
        viewModelScope.launch {
            useCase.initSampleData().collect {
                _uiState.value = it
            }
        }
    }
}