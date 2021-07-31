package com.felipepolo.pruebaenvivo.domain

import com.apollographql.apollo.api.Response
import com.felipepolo.GetCharactersQuery
import com.felipepolo.pruebaenvivo.data.Result
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface HomeRepositoryInt {
    fun getCharacters(page:Int): Observable<Response<GetCharactersQuery.Data>>
    suspend fun saveFavoriteCharacter(charactersEntity: CharactersEntity): Completable
    suspend fun deleteFavoriteCharacter(charactersEntity: CharactersEntity): Completable
    suspend fun getAllFavoriteCharacter():  Result<List<CharactersEntity>>
}