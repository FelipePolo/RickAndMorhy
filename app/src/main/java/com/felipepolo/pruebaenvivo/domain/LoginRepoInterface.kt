package com.felipepolo.pruebaenvivo.domain

interface LoginRepoInterface {

    suspend fun autenticateUser(email: String,password: String)
    suspend fun registerUser(email: String, password: String)
}