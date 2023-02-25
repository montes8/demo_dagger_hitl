package com.challenge.demodaggerhilt.repository.api

import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.adapter.MapperResponse
import com.challenge.demodaggerhilt.repository.adapter.getResultOrThrowException
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class DataKoinNetworkTest {

    lateinit var mainRepository: DataKoinNetwork

    @Mock
    lateinit var apiService: ServiceApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = DataKoinNetwork(apiService)
    }


    @Test
    fun `get all list test`() {
        runBlocking {
            Mockito.`when`(apiService.getListKoin()).thenReturn(MapperResponse.from(Response.success(testList)))
            val response = mainRepository.getList()
            Assert.assertEquals(testList, MapperResponse.from(Response.success(response)).getResultOrThrowException())
        }
    }

    @Test
    fun `get all list test two`() {
        runBlocking {
            Mockito.`when`(apiService.getListKoin()).thenReturn(MapperResponse.from(Response.success(testList)))
            val response = mainRepository.getListService()
            Assert.assertEquals(testList, response.getResultOrThrowException())
        }

    }
}