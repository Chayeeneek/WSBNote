package com.example.notatnik

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NotatkiDane : RoomDatabase() {

    abstract fun getNotatkiDao(): NotatkiDao

    companion object {

        @Volatile
        private var INSTANCE: NotatkiDane? = null

        fun getDatabase(context: Context): NotatkiDane {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotatkiDane::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}