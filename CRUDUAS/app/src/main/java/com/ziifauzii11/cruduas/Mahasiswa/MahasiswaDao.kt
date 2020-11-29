package com.ziifauzii11.cruduas.Mahasiswa

import androidx.room.*

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun addMhs(mahasiswa: Mahasiswa)

    @Query("SELECT * FROM mahasiswa ORDER BY id DESC")
    suspend fun getMhs() : List<Mahasiswa>

    @Query("SELECT * FROM mahasiswa WHERE id=:mahasiswa_id")
    suspend fun getMhs(mahasiswa_id: Int) : List<Mahasiswa>

    @Update
    suspend fun updateMhs(mahasiswa: Mahasiswa)

    @Delete
    suspend fun deleteMhs(mahasiswa: Mahasiswa)
}