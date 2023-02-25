package com.challenge.demodaggerhilt.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import com.challenge.demodaggerhilt.ui.splash.AppViewModel
import com.challenge.demodaggerhilt.utils.testList
import com.challenge.demodaggerhilt.utils.testListTwo
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
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
    fun `validate data list correct`() = runBlockingTest{
        val a :IAppRepositoryNetwork  = dataKoinNetwork
        //Mockito.`when`(a.getList()).thenReturn(testList)
        val result =  a.getList()
        Assert.assertEquals(result, testList)
    }

 /*   @Test
    fun `validate data list incorrect`() = runBlockingTest{
        val a :IAppRepositoryNetwork  = dataKoinNetwork
        val dataKoinUseCase = DataKoinUseCase(a)
        Mockito.`when`(dataKoinUseCase.getList("gabbi")).thenReturn(arrayListOf("Title 2"))
        val result =  dataKoinUseCase.getList("gabbi")
        Assert.assertEquals(result,testList)
    }*/
}