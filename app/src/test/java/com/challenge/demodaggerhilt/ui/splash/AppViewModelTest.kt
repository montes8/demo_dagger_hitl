package com.challenge.demodaggerhilt.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.demodaggerhilt.ui.home.HomeTwoViewModel
import com.challenge.demodaggerhilt.usecases.AppUseCase
import com.challenge.demodaggerhilt.usecases.DataHiltUseCase
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify

class AppViewModelTest{

     @Mock
     lateinit var appUseCase: AppUseCase

     @Mock
     lateinit var observer: Observer<Boolean>

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
     fun `get load login`() = runBlockingTest{
         val vm = AppViewModel(appUseCase,dispatcher)
         Mockito.`when`(appUseCase.getUserTwo()).thenReturn(true)
         vm.successUserTwoLiveData.observeForever(observer)
         vm.getUser("gabbi","gabbi")
         verify(observer).onChanged(true)

     }
}