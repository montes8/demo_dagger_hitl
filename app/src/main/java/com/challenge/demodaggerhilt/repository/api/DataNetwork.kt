package com.challenge.demodaggerhilt.repository.api


import com.challenge.demodaggerhilt.utils.testList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

open class DataNetwork : ListenerLocalDataSource{

     override fun getList(): Flow<List<String>> {
        return flowOf(testList)
    }

}


interface ListenerLocalDataSource {
    fun getList(): Flow<List<String>>
}