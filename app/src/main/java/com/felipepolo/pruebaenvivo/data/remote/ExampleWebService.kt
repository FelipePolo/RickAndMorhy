package com.felipepolo.pruebaenvivo.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ExampleWebService {
     @GET("someSubRoute/param = {somevalue}")
     fun getSomeThing(@Path("param") param: String): List<Int>
}