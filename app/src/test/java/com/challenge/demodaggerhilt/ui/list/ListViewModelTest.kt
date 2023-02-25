package com.challenge.demodaggerhilt.ui.list

import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.ui.ListViewModel
import com.challenge.demodaggerhilt.usecases.DataUseCase
import com.challenge.demodaggerhilt.utils.FakeDataNetwork
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ListViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

   //Escuchar lista Flow emite del servidor
    @Test
    fun `Listen list Flow emits from the server`() = runBlockingTest {
        val repository = DataUseCase(FakeDataNetwork())
        val vm = ListViewModel(repository)

        vm.list.collect{
            Assert.assertEquals(testList,it)
        }
    }

}