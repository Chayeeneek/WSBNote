package com.example.notatnik

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.util.concurrent.Flow

class Magazyn(private val notatkiDao: NotatkiDao) {

    val allNotes: LiveData<List<Note>> = notatkiDao.getAllNotes()

    suspend fun insert(note: Note) {
        notatkiDao.insert(note)
    }

    suspend fun delete(note: Note){
        notatkiDao.delete(note)
    }

    suspend fun update(note: Note){
        notatkiDao.update(note)
    }
}