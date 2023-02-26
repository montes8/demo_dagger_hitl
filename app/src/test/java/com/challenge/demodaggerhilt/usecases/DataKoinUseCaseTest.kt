package com.challenge.demodaggerhilt.usecases

import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DataKoinUseCaseTest{

    @Mock
    lateinit var dataKoinNetwork: DataKoinNetwork

    @Test
    fun `validate data list correct`() = runBlocking{
        val dataKoinUseCase = DataKoinUseCase(dataKoinNetwork)
        Mockito.`when`(dataKoinNetwork.getList()).thenReturn(testList)
        val result =  dataKoinUseCase.getList()
        Assert.assertEquals(result, testList)
    }

   @Test
    fun `validate data list incorrect`() = runBlocking{
       val dataKoinUseCase = DataKoinUseCase(dataKoinNetwork)
        Mockito.`when`(dataKoinUseCase.getList()).thenReturn(arrayListOf("Title 2"))
        val result =  dataKoinUseCase.getList()
        Assert.assertTrue(result != testList)
    }
}