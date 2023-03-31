package com.example.resultmonadsample.domain.repository

import com.example.resultmonadsample.domain.network.Api
import com.example.resultmonadsample.domain.network.ApiImpl
import com.example.resultmonadsample.domain.network.SampleResponse
import com.example.resultmonadsample.domain.util.ProcessResult

class SampleRepositoryImpl(
    private val api: Api = ApiImpl()
) : SampleRepository {
    override suspend fun getSampleModel(): ProcessResult<SampleResponse> {
        return api.getSampleResult()
    }
}