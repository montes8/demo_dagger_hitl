package com.challenge.demodaggerhilt.ui.list_hilt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.repository.api.DataHiltNetwork
import com.challenge.demodaggerhilt.usecases.DataHiltUseCase
import com.challenge.demodaggerhilt.usecases.DataKoinUseCase
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListHiltViewModelTest{

    @Mock
    lateinit var appUseCase: DataHiltUseCase

    @Mock
    lateinit var observer: Observer<List<String>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `get list of server`() = runBlocking{
        `when`(appUseCase.getList()).thenReturn(testList)
       val vm = ListHiltViewModel(appUseCase,coroutineTestRule.dispatcher)
        vm.successListLiveData.observeForever(observer)
        vm.getList()
        verify(observer).onChanged(testList)
    }
}