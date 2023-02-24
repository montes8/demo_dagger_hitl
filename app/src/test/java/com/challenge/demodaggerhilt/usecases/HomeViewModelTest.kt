package com.challenge.demodaggerhilt.usecases

import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.ui.home.HomeViewModel
import com.challenge.demodaggerhilt.utils.FakeDataNetwork
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

   //Escuchar lista Flow emite del servidor
    @Test
    fun `Listen list Flow emits from the server`() = runBlockingTest {
        val repository = DataUseCase(FakeDataNetwork())
        val vm = HomeViewModel(repository)

        vm.list.collect{
            Assert.assertEquals(testList,it)
        }
    }



}