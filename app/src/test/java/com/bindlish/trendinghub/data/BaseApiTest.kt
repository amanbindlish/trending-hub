package com.bindlish.trendinghub.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bindlish.trendinghub.utils.LiveDataCallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * Created by Aman Bindlish on 24,September,2019
 */
@RunWith(JUnit4::class)
open class BaseApiTest<T> {

    lateinit var mockWebServer: MockWebServer

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    @Throws(IOException::class)
    fun mockServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    @Throws(IOException::class)
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Throws(IOException::class)
    fun enqueueResponse(fileName: String) {
        enqueueResponse(fileName, Collections.emptyMap())
    }

    @Throws(IOException::class)
    fun enqueueResponse(fileName: String, headers: Map<String, String>) {
        val inputStream = BaseApiTest::class.java.classLoader?.getResourceAsStream(
            String.format(
                "api-response/%s",
                fileName
            )
        )!!
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }

    fun createService(clazz: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(clazz)
    }
}