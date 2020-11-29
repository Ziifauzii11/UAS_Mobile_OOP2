package com.ziifauzii11.cruduas.Mahasiswa

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama: String,
    val alamat: String
)