package com.felipepolo.pruebaenvivo.data.local

import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dataBase: AppDataBase) {

    fun saveFavoriteCharacter(charactersEntity: CharactersEntity): Completable {
        return dataBase.charactersDao().saveFavoriteCharacter(charactersEntity)
    }

    suspend fun getCharacter(id: String): CharactersEntity? {
        return dataBase.charactersDao().getCharacter(id)
    }

    suspend fun getFavoritesCharacter(userId: String): List<CharactersEntity>? {
        val list = dataBase.charactersDao().getAllUserFavoriteCharacter(userId)
        return if (list.isNotEmpty()) {
            list
        } else {
            null
        }
    }

    suspend fun deleteAllFavorites(){
        dataBase.charactersDao().deleteAllFavorites()
    }

    fun deleteFavoriteCharacter(charactersEntity: CharactersEntity): Completable {
       return dataBase.charactersDao().deleteFavorite(charactersEntity)
    }

}