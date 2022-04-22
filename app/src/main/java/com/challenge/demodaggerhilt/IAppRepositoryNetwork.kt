package com.challenge.demodaggerhilt


interface IAppRepositoryNetwork {
     suspend fun getUser():User
}