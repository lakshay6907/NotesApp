package com.example.notesapp

import androidx.lifecycle.LiveData
import com.example.notesapp.data.Notes
import com.example.notesapp.data.NotesDao

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertNotes(notes: Notes){
        notesDao.insert(notes)
    }

    suspend fun deleteNotes(notes: Notes){
        notesDao.delete(notes)
    }
}