package com.example.unscramble.ui

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.unscramble.data.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    // Mengambil semua kata. Menggunakan Flow agar UI otomatis terupdate jika ada data baru
    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<WordEntity>>

    // Menyimpan kata baru, abaikan jika kata sudah ada
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: WordEntity)
}