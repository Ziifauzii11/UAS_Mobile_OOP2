package com.ziifauzii11.cruduas.Dosen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dosen(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama: String,
    val alamat: String
)