package com.felipepolo.pruebaenvivo.domain

import com.apollographql.apollo.api.Response
import com.felipepolo.GetCharactersQuery
import com.felipepolo.pruebaenvivo.data.local.LocalDataSource
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import com.felipepolo.pruebaenvivo.data.remote.RemoteDataSource
import com.felipepolo.pruebaenvivo.data.Result
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HomeRepoImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val userAuth: FirebaseAuth
    ): HomeRepositoryInt {

    override fun getCharacters(page: Int): Observable<Response<GetCharactersQuery.Data>> {
        return remoteDataSource.getCharacters(page)
    }

    override suspend fun saveFavoriteCharacter(charactersEntity: CharactersEntity): Completable {
        if(localDataSource.getCharacter(charactersEntity.id) == null) {
            remoteDataSource.saveFavoriteUserCharacter(charactersEntity)
        }
        return localDataSource.saveFavoriteCharacter(charactersEntity)
    }

    override suspend fun getAllFavoriteCharacter(): Result<List<CharactersEntity>> {
        localDataSource.getFavoritesCharacter(userAuth.uid!!)?.let {
            return Result.Success(it)
        }
        localDataSource.deleteAllFavorites()
        val characterList = remoteDataSource.getFavoriteCharacters(userAuth.uid!!)
        if(characterList.isNotEmpty()) {
            characterList.forEach {
                localDataSource.saveFavoriteCharacter(it)
            }
        }
        return Result.Success(characterList)
    }

    override suspend fun deleteFavoriteCharacter(charactersEntity: CharactersEntity): Completable {
        remoteDataSource.deleteFavoriteCharacter(charactersEntity)
        return localDataSource.deleteFavoriteCharacter(charactersEntity)
    }
}