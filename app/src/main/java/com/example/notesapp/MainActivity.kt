package com.example.notesapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Notes
import com.example.notesapp.data.NotesDao
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.databinding.ItemNoteBinding
import com.example.notesapp.model.NotesViewModel


class MainActivity : AppCompatActivity(), NotesRVAdapter {
    lateinit var viewModel: NotesViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //setContentView(R.layout.activity_main)

        val recyclerView = binding.rvNotes
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application), ).get(NotesViewModel::class.java)

        viewModel.allnotes.observe(this, Observer{list->
           list?.let {
               adapter.updateList(it)
           }
        })
    }

    override fun onItemClicked(notes: Notes) {
        viewModel.deleteNote(notes)
    }

    fun submitData(view: View) {
        val noteText = binding.input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Notes(0,noteText))
        }
    }


}