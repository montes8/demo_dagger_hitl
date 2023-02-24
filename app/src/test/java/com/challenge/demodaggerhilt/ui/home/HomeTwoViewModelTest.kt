package com.challenge.demodaggerhilt.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.demodaggerhilt.usecases.DataHiltUseCase
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeTwoViewModelTest{


    @Mock lateinit var appUseCase: DataHiltUseCase

    @Mock lateinit var observer: Observer<List<String>>

    @get:Rule
    val rule = InstantTaskExecutorRule()


    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun listenServer() {
        Assert.assertTrue(true)
    }

    @Test
    fun `get list of server`() = runBlockingTest{
        `when`(appUseCase.getList()).thenReturn(testList)
       val vm = HomeTwoViewModel(appUseCase,dispatcher)
        vm.successListLiveData.observeForever(observer)
        vm.getList()
        verify(observer).onChanged(testList)
    }
}