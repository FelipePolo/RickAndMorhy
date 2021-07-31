package com.felipepolo.pruebaenvivo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity

@Database(entities = [CharactersEntity::class],version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}