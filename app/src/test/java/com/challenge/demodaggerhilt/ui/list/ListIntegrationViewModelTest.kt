package com.challenge.demodaggerhilt.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.getOrAwaitValue
import com.challenge.demodaggerhilt.repository.ServiceApi
import com.challenge.demodaggerhilt.repository.api.DataNetwork
import com.challenge.demodaggerhilt.ui.ListViewModel
import com.challenge.demodaggerhilt.usecases.DataUseCase
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
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
class ListIntegrationViewModelTest {

    lateinit var mainViewModel: ListViewModel
    lateinit var mainRepository: DataNetwork
    lateinit var mainUseCase: DataUseCase

    @Mock
    lateinit var apiService: ServiceApi

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        mainRepository = DataNetwork(apiService)
        mainUseCase = DataUseCase(mainRepository)
        mainViewModel = ListViewModel(mainUseCase)

    }

    @Test
    fun getAllListTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getListService()).thenReturn(Response.success(testList))
            mainViewModel.getListService()
            val result = mainViewModel.successListLiveData.getOrAwaitValue()
            assertEquals(testList, result)
        }
    }


    @Test
    fun `empty list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getListService())
                .thenReturn(Response.success(listOf<String>()))
            mainViewModel.getListService()
            val result = mainViewModel.successListLiveData.getOrAwaitValue()
            assertEquals(listOf<String>(), result)
        }
    }

}