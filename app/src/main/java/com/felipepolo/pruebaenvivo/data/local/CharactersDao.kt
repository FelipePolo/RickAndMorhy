package com.felipepolo.pruebaenvivo.data.local

import androidx.room.*
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import io.reactivex.rxjava3.core.Completable

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters WHERE userId = :userId")
    suspend fun getAllUserFavoriteCharacter(userId: String): List<CharactersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavoriteCharacter(charactersEntity: CharactersEntity): Completable

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacter(id:String): CharactersEntity?

    @Query("DELETE FROM characters")
    suspend fun deleteAllFavorites()

    @Delete
    fun deleteFavorite(charactersEntity: CharactersEntity): Completable
}