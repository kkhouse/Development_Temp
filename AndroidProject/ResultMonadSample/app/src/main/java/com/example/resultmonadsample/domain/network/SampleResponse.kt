package com.example.resultmonadsample.domain.network

import com.example.resultmonadsample.domain.util.SampleModel
import com.example.resultmonadsample.domain.util.Data as ModelData

data class SampleResponse(
    val success: Boolean,
    val data: ResponseData
)

data class ResponseData(
    val name: String,
    val note: String,
    val age: Int,
    val registerDate: String
)

fun SampleResponse.convertModel(): SampleModel {
    return SampleModel(
        data = ModelData(
            name = this.data.name,
            note = this.data.note,
            age = this.data.age,
            registerDate = this.data.registerDate
        )
    )
}