package com.example.notesapp.model

import android.app.Application
import androidx.lifecycle.*
import com.example.notesapp.NoteRepository
import com.example.notesapp.data.Notes
import com.example.notesapp.data.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val repository: NoteRepository
    val allnotes: LiveData<List<Notes>>

    init {
        val dao = NotesDatabase.getDatabase(application).notesDao()
        repository = NoteRepository(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(notes)
        }
    }

    fun insertNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNotes(notes)
        }
    }
}
