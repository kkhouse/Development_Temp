package com.example.resultmonadsample.domain.network

import com.example.resultmonadsample.App.Companion.TIMBER_TAG
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import timber.log.Timber
import java.util.concurrent.TimeUnit


interface Service {
    @GET("/")
    suspend fun getSampleResponse(): Response<SampleResponse>
}

fun getService(baseUrl: String): Service {
    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.SECONDS)
        .readTimeout(3, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
//        .add(CustomJsonAdapter.FACTORY)
        .build()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(Service::class.java)
}

interface MockService {
    suspend fun getSampleResponse(): MockResponse<SampleResponse>
}

data class MockResponse<T>(val data: T)

class MockServiceImpl(
    private val randomData: List<ResponseData> = listOf(
        ResponseData("name1", "notenotenotenotenote", 12, registerDate = "19991212"),
        ResponseData("name2", "notenotenotenotenote", 24, registerDate = "19991111"),
        ResponseData("name3", "notenotenotenotenote", 33, registerDate = "19991010"),
        ResponseData("name4", "notenotenotenotenote", 11, registerDate = "19991113"),
        ResponseData("name5", "notenotenotenotenote", 14, registerDate = "19991229"),
    )
): MockService {

    override suspend fun getSampleResponse(): MockResponse<SampleResponse> {
        Timber.tag(TIMBER_TAG).d("get api request with delay 2s")
        delay(2000)
        return MockResponse(
            SampleResponse(
                success = true,
                data = randomData[randomData.indices.random()]
            )
        )
    }
}

//
//class CustomJsonAdapter (private val moshi: Moshi) : JsonAdapter<List<com.example.resultmonadsample.domain.data.SampleResponse?>>() {
//
//    override fun fromJson(reader: JsonReader): List<com.example.resultmonadsample.domain.data.SampleResponse?> {
//        reader.beginArray()
//        val list = arrayListOf<com.example.resultmonadsample.domain.data.SampleResponse?>()
//        val adapter = moshi.adapter(com.example.resultmonadsample.domain.data.SampleResponse::class.java)
//        while (reader.hasNext()) {
//            list.add(adapter.fromJson(reader))
//        }
//        reader.endArray()
//        return list
//    }
//
//    override fun toJson(writer: JsonWriter, value: List<com.example.resultmonadsample.domain.data.SampleResponse?>?) {
//    }
//
//    companion object {
//        val FACTORY: Factory = Factory { type, _, moshi ->
//            val listType = Types.newParameterizedType(ArrayList::class.java, com.example.resultmonadsample.domain.data.SampleResponse::class.java)
//            when(type) {
//                listType -> CustomJsonAdapter(moshi)
//                else -> null
//            }
//        }
//    }
//}