package com.example.notatnik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotatkiWidok (application: Application) :AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    val repository : Magazyn

    init {
        val dao = NotatkiDane.getDatabase(application).getNotatkiDao()
        repository = Magazyn(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote (note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}