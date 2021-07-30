package com.felipepolo.pruebaenvivo.data.local.models

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ExampleDao {

    @Query("SELECT * FROM characters")
    suspend fun getAllExampleInfo(): List<CharactersEntity>
}