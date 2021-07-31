package com.felipepolo.pruebaenvivo.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx3.rxQuery
import com.felipepolo.GetCharactersQuery
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apolloClient: ApolloClient,
    private val fireStore: FirebaseFirestore
) {

    fun getCharacters(page: Int): Observable<Response<GetCharactersQuery.Data>> {
        return apolloClient.rxQuery(GetCharactersQuery(page))
    }

    fun saveFavoriteUserCharacter(charactersEntity: CharactersEntity) {
        fireStore.collection("favorites")
            .add(charactersEntity)
    }

    suspend fun getFavoriteCharacters(userId: String): List<CharactersEntity> {
        return fireStore.collection("favorites")
            .get()
            .await()
            .documents.map {
                CharactersEntity(
                    it.getField("id")!!,
                    it.getField("name"),
                    it.getField("image"),
                    it.getField("gender"),
                    it.getField("type"),
                    it.getField("species"),
                    it.getField("status"),
                    it.getField("userId")!!,
                    true
                )
            }.filter {
                it.userId == userId
            }
    }

    suspend fun deleteFavoriteCharacter(charactersEntity: CharactersEntity) {
        val documentId =  findDocId(charactersEntity)
        fireStore.collection("favorites")
            .document(documentId)
            .delete()
            .await()
    }

    suspend fun findDocId(charactersEntity: CharactersEntity): String {
        return fireStore.collection("favorites")
            .get()
            .await()
            .documents.filter { it.getField<String>("id") == charactersEntity.id }
            .get(0).id
    }

}