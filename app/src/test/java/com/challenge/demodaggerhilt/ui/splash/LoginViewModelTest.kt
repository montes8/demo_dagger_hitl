package com.challenge.demodaggerhilt.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.usecases.AppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest{

     @Mock
     lateinit var loginUseCase: AppUseCase

     @Mock
     lateinit var observer: Observer<Boolean>

     @get:Rule
     val rule = InstantTaskExecutorRule()


    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

     @Test
     fun `get load login`() = runBlockingTest{
         val vm = LoginBasicViewModel(coroutineTestRule.dispatcher)
        // `when`(loginUseCase.getUserTwo()).thenReturn(true)
         vm.successUserLiveData.observeForever(observer)
         vm.getUser("gabbi","gabbi")
         verify(observer).onChanged(true)
     }

}