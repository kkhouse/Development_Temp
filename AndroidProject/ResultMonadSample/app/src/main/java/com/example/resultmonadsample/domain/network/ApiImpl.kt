package com.example.resultmonadsample.domain.network

import com.example.resultmonadsample.App.Companion.TIMBER_TAG
import com.example.resultmonadsample.domain.util.AppError
import com.example.resultmonadsample.domain.util.ProcessResult
import com.example.resultmonadsample.domain.util.SampleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

interface Api {
    suspend fun getSampleResult(): ProcessResult<SampleResponse>
}

class ApiImpl constructor(
//    private val service: com.example.resultmonadsample.domain.data.Service = Network.com.example.resultmonadsample.domain.data.getService("http://baseUrl"),
    private val service: MockService = MockServiceImpl(),
    private val coroutineContext: CoroutineContext = Dispatchers.IO,
//    private val handler: HogeHandler
) : Api {

    override suspend fun getSampleResult(): ProcessResult<SampleResponse> {
        return withContext(coroutineContext) {
            try {
                // handler(service.getSampleResponse())
                ProcessResult.Success(service.getSampleResponse().data)
            } catch (e: Exception) {
                Timber.tag(TIMBER_TAG).d("exception message : ${e.message}")
                ProcessResult.Failure(AppError.UnknownError(e))
            }
        }
    }
}