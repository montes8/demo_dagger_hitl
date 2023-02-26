package com.challenge.demodaggerhilt.ui.list_koin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.readAndParseFileFromAssets
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import com.challenge.demodaggerhilt.repository.api.DataNetwork
import com.challenge.demodaggerhilt.ui.ListViewModel
import com.challenge.demodaggerhilt.usecases.DataUseCase
import com.challenge.demodaggerhilt.utils.testListJson
import com.google.gson.Gson
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListIntegrationKoinWebServerTest {

    lateinit var mainRepository: DataNetwork
    lateinit var mainUseCase: DataUseCase
    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: ServiceApi
    lateinit var gson: Gson

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ServiceApi::class.java)
          mainRepository = DataNetwork(apiService)
          mainUseCase = DataUseCase(mainRepository)
    }

    @Test
    fun getAllListTest() = runBlocking {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(testListJson))
        val response = mainUseCase.getListService()
        Assert.assertEquals(true, response.body() == readAndParseFileFromAssets(testListJson))
        }


    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}