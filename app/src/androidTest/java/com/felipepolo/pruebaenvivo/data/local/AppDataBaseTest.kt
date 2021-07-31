package com.felipepolo.pruebaenvivo.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class AppDataBaseTest {

    private lateinit var database: AppDataBase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun testIsDatabaseNotOpen() {
        assertThat(database.isOpen).isFalse()
    }

    @Test
    fun testDatabasePathIsMemory() = runBlocking {
        executeDatabaseFunction()
        assertThat(database.openHelper.readableDatabase.path).isEqualTo(":memory:")
    }

    @Test
    fun testDatabaseVersionIsCurrent() = runBlocking {
        executeDatabaseFunction()
        assertThat(database.openHelper.readableDatabase.version).isEqualTo(1)
    }

    private fun executeDatabaseFunction() = runBlocking {
        val charactersEntity = CharactersEntity(
            "1",
            "Rick",
            "some Img.png",
            "female",
            "human",
            "human",
            "alive",
            "some user ID",
            true
        )
        database.charactersDao().saveFavoriteCharacter(charactersEntity)
    }

    @After
    fun tearDown() {
        database.close()
    }
}