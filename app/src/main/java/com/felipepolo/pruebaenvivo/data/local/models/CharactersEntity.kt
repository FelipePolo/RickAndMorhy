package com.felipepolo.pruebaenvivo.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.felipepolo.GetCharactersQuery

@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey
    @ColumnInfo
    var id: String,
    @ColumnInfo
    var name: String? = "",
    @ColumnInfo
    var image: String? = "",
    @ColumnInfo
    var gender: String? = "",
    @ColumnInfo
    var type: String? = "",
    @ColumnInfo
    var species: String? = "",
    @ColumnInfo
    var status: String? = "",
    @ColumnInfo
    var userId: String,
    @ColumnInfo
    var favorite: Boolean = false
)

fun CharactersEntity.toFavorite(): CharactersEntity {
    this.favorite = true
    return this
}

fun toCharacterEntityList(data: GetCharactersQuery.Data,userEmail: String): List<CharactersEntity> {
    return data.characters!!.results!!.map {
        CharactersEntity(it?.id!!,it.name!!,it.image!!,it.gender!!,it.type!!,it.species!!,it.status!!,userEmail)
    }
}