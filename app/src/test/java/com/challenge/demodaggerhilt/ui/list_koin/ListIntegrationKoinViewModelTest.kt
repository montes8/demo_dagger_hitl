package com.challenge.demodaggerhilt.ui.list_koin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.getOrAwaitValue
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.adapter.MapperResponse
import com.challenge.demodaggerhilt.repository.adapter.getResultOrThrowException
import com.challenge.demodaggerhilt.repository.api.DataHiltNetwork
import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import com.challenge.demodaggerhilt.repository.api.DataNetwork
import com.challenge.demodaggerhilt.ui.ListViewModel
import com.challenge.demodaggerhilt.usecases.DataHiltUseCase
import com.challenge.demodaggerhilt.usecases.DataKoinUseCase
import com.challenge.demodaggerhilt.usecases.DataUseCase
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListIntegrationKoinViewModelTest {

    lateinit var mainViewModel: ListKoinViewModel
    lateinit var mainRepository: DataKoinNetwork
    lateinit var mainUseCase: DataKoinUseCase

    @Mock lateinit var apiService: ServiceApi


    @Mock
    lateinit var observer: Observer<List<String>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()


    @Before
    fun setup() {
        mainRepository = DataKoinNetwork(apiService)
        mainUseCase = DataKoinUseCase(mainRepository)
        mainViewModel = ListKoinViewModel(mainUseCase,coroutineTestRule.dispatcher)

    }

    @Test
    fun getAllListTest() = runBlockingTest {
            Mockito.`when`(apiService.getListKoin()).thenReturn(MapperResponse.from(Response.success(testList)))
            mainViewModel.getList()
            mainViewModel.successListLiveData.observeForever(observer)
            Mockito.verify(observer).onChanged(testList)
        }


    @Test
    fun `empty list test`() {
        runBlocking {
           Mockito.`when`(apiService.getListKoin()).
           thenReturn(MapperResponse.from(Response.success(listOf<String>())))
            mainViewModel.getList()
            val result = mainViewModel.successListLiveData.getOrAwaitValue()
            assertEquals(listOf<String>(), result)
        }
    }

}