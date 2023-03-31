package com.example.resultmonadsample.usecase

import com.example.resultmonadsample.domain.repository.SampleRepositoryImpl
import com.example.resultmonadsample.domain.repository.SampleRepository
import com.example.resultmonadsample.ui.uistate.ErrorDialogType
import com.example.resultmonadsample.ui.uistate.SampleScreenUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleUseCaseImpl(
    private val sampleRepository: SampleRepository = SampleRepositoryImpl()
): SampleUseCase {

    /**
     * 基本的な使い方
     */
    override suspend fun initSampleData(): Flow<SampleScreenUiState> {
        return flow {
            sampleRepository.getSampleModel().forEachAsync(
                onSuccess = {
                    emit(
                        SampleScreenUiState(
                            isLoading = false,
                            name = it.data.name,
                            note = it.data.note
                        )
                    )
                },
                onFailure = {
                    emit(
                        SampleScreenUiState(
                            isLoading = false,
                            isErrorDialogShown = true,
                            errorDialogType = ErrorDialogType.fromAppError(it)
                        )
                    )
                },
                onLoading = {
                    emit(
                        SampleScreenUiState(
                            isLoading = true
                        )
                    )
                }
            )
        }
    }
}