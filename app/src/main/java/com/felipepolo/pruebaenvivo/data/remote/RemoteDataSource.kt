package com.felipepolo.pruebaenvivo.data.remote

import com.felipepolo.pruebaenvivo.data.Result
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val webService: ExampleWebService) {

    fun getSomeThing(id:String): Result<List<Int>>{

        return Result.Success(
            webService.getSomeThing(id)
        )
    }

}