package com.challenge.demodaggerhilt.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.demodaggerhilt.CoroutineTestRule
import com.challenge.demodaggerhilt.repository.api.DataKoinNetwork
import com.challenge.demodaggerhilt.usecases.DataKoinUseCase
import com.challenge.demodaggerhilt.usecases.DataUseCase
import com.challenge.demodaggerhilt.usecases.IAppRepositoryNetwork
import com.challenge.demodaggerhilt.utils.testList
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.modules.junit4.PowerMockRunner
import java.util.*

val lookUpLeagueModule = module {
    single { DataKoinUseCase() }
    single<IAppRepositoryNetwork> { DataKoinNetwork() }
    viewModel { HomeThreeViewModel() }
}

@RunWith(PowerMockRunner::class)
class HomeThreeViewModelTest{

    companion object {
        private const val EMAIL = "abc@example.com"
        private const val PASSWORD = "123456"

        //Method annotated with BeforeClass will be called once before all class tests execute and should be static
        @BeforeClass
        @JvmStatic
        fun setup() {
            println("Before Class")
        }

        //Method annotated with AfterClass will be called once after all class tests execute and should be static
        @AfterClass
        @JvmStatic
        fun teardown() {
            println("After Class")
        }
    }

    private lateinit var loginViewModel: HomeThreeViewModel

    @Mock
    private lateinit var serviceUtil: DataKoinUseCase

    @Mock lateinit var observer: Observer<List<String>>

    private inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()


    @Rule
    @JvmField
    val coRoutineTestRule = CoroutineTestRule()

    @Before
    fun before() {
        // Enable static mocking for all methods of a class.
        PowerMockito.mockStatic(UtilityClass::class.java)

        // Initializing the class to be tested
        loginViewModel = HomeThreeViewModel()
        loginViewModel.getObserverState().observeForever(mockObserverForStates)
    }


}