package com.example.resultmonadsample.usecase

import com.example.resultmonadsample.ui.uistate.SampleScreenUiState
import kotlinx.coroutines.flow.Flow

interface SampleUseCase {
    suspend fun initSampleData(): Flow<SampleScreenUiState>
}