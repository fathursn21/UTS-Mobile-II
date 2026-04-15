package com.example.unscramble.ui

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.unscramble.data.WordEntity
@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var Instance: WordDatabase? = null

        private lateinit var appContext: Context
        fun init(context: Context) {
            appContext = context.applicationContext
        }

        fun getDatabase(): WordDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    appContext,
                    WordDatabase::class.java,
                    "word_database"
                ).build().also { Instance = it }
            }
        }
    }
}