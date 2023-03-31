package com.example.resultmonadsample.domain.repository

import com.example.resultmonadsample.domain.network.SampleResponse
import com.example.resultmonadsample.domain.util.ProcessResult

interface SampleRepository {
    suspend fun getSampleModel(): ProcessResult<SampleResponse>
}