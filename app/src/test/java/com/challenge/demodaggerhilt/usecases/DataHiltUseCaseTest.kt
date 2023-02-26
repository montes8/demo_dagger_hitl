package com.challenge.demodaggerhilt.usecases

import com.challenge.demodaggerhilt.repository.api.DataHiltNetwork
import com.challenge.demodaggerhilt.utils.testList
import com.challenge.demodaggerhilt.utils.testListTwo
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataHiltUseCaseTest{

    @Mock
    lateinit var dataKoinNetwork: DataHiltNetwork

    @Test
    fun `validate data list correct`() = runBlockingTest{
        Mockito.`when`(dataKoinNetwork.getList()).thenReturn(testList)
        val result =  dataKoinNetwork.getList()
        Assert.assertEquals(result, testListTwo)
    }

}