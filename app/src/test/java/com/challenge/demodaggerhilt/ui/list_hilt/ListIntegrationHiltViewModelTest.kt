package com.challenge.demodaggerhilt.ui.list_hilt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.getOrAwaitValue
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.adapter.MapperResponse
import com.challenge.demodaggerhilt.repository.api.DataHiltNetwork
import com.challenge.demodaggerhilt.usecases.DataHiltUseCase
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListIntegrationHiltViewModelTest {

    lateinit var mainViewModel: ListHiltViewModel
    lateinit var mainRepository: DataHiltNetwork
    lateinit var mainUseCase: DataHiltUseCase

   @Mock lateinit var apiService: ServiceApi

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()


    @Before
    fun setup() {
        mainRepository = DataHiltNetwork(apiService)
        mainUseCase = DataHiltUseCase(mainRepository)
        mainViewModel = ListHiltViewModel(mainUseCase,coroutineTestRule.dispatcher)

    }

    @Test
    fun getAllListTest() = runBlocking {
        Mockito.`when`(apiService.getListHilt()).thenReturn(MapperResponse.from(Response.success(testList)))
            mainViewModel.getList()
            val result = mainViewModel.successListLiveData.getOrAwaitValue()
            assertEquals(testList, result)
        }


    @Test
    fun `empty list test`() {
        runBlocking {
            Mockito.`when`(apiService.getListHilt()).thenReturn(MapperResponse.from(Response.success(listOf<String>())))
            mainViewModel.getList()
            val result = mainViewModel.successListLiveData.getOrAwaitValue()
            assertEquals(listOf<String>(), result)
        }
    }

}