package com.felipepolo.pruebaenvivo.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey
    @ColumnInfo
    var id: Int,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var apellido: String

)