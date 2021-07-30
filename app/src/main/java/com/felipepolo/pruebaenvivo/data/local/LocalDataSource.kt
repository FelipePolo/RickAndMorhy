package com.felipepolo.pruebaenvivo.data.local

import com.felipepolo.pruebaenvivo.data.Result
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dataBase: AppDataBase) {

    suspend fun getAllUser(): Result<List<CharactersEntity>> {
        return Result.Success(dataBase.exampleDao().getAllExampleInfo())
    }
}