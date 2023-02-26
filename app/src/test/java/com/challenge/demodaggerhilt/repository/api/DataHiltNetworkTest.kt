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
class DataHiltNetworkTest {

    lateinit var mainRepository: DataHiltNetwork

    @Mock
    lateinit var apiService: ServiceApi

    @Before
    fun setup() {
        mainRepository = DataHiltNetwork(apiService)
    }

    @Test
    fun `get all list test`() {
        runBlocking {
            Mockito.`when`(apiService.getListHilt()).thenReturn(MapperResponse.from(Response.success(testList)))
            val response = mainRepository.getList()
            Assert.assertEquals(testList, MapperResponse.from(Response.success(response)).getResultOrThrowException())
        }
    }
}