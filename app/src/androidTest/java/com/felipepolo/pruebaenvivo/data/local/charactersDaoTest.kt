package com.felipepolo.pruebaenvivo.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class charactersDaoTest {

    private lateinit var database: AppDataBase
    private lateinit var dao: CharactersDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
        dao = database.charactersDao()
    }

    @Test
    fun testSaveFavoriteCharacter() {
        val character = getExampleCharacyer()
        dao.saveFavoriteCharacter(character).subscribe({
            runBlocking {
                val favoriteCharacter = dao.getCharacter(character.id)
                assertThat(favoriteCharacter).isEqualTo(character)
            }
        },{})
    }

    @Test
    fun testGetAllFavoriteCharacters() {
        val character = getExampleCharacyer()
        dao.saveFavoriteCharacter(character).subscribe({
            runBlocking {
                val favoriteList = dao.getAllUserFavoriteCharacter(character.userId)
                assertThat(favoriteList).isNotEmpty()
                assertThat(favoriteList).hasSize(1)
            }
        },{})
    }

    @Test
    fun testDeleteAllFavorites() = runBlocking {
        val character = getExampleCharacyer()
        dao.deleteAllFavorites()
        val favoriteCharacter = dao.getCharacter(character.id)
        assertThat(favoriteCharacter).isEqualTo(null)
    }

    private fun getExampleCharacyer(): CharactersEntity {
        return CharactersEntity(
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
    }
}