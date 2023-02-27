package com.challenge.demodaggerhilt.ui.list

import com.challenge.demodaggerhilt.ui.ListViewModel
import com.challenge.demodaggerhilt.usecases.DataUseCase
import com.challenge.demodaggerhilt.utils.FakeDataNetwork
import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListViewModelTest {

    @Test
    fun `Listen list Flow emits from the server`(): Unit = runBlocking {
        val repository = DataUseCase(FakeDataNetwork())
        val vm = ListViewModel(repository)

        vm.list.map {
            Assert.assertEquals(testList,it)
        }
    }

}