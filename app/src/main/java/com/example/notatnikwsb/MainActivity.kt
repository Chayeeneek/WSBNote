package com.example.notatnik

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {


    lateinit var Widok: NotatkiWidok
    lateinit var notatkiRV: RecyclerView
    lateinit var Button: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notatkiRV = findViewById(R.id.notesRV)
        Button = findViewById(R.id.idFAB)


        notatkiRV.layoutManager = LinearLayoutManager(this)


        val noteRVAdapter = NotatkiAdapter(this, this, this)

        notatkiRV.adapter = noteRVAdapter

        Widok = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotatkiWidok::class.java)

        Widok.allNotes.observe(this, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        Button.setOnClickListener {
            val intent = Intent(this@MainActivity, DodajEdytuj::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, DodajEdytuj::class.java)
        intent.putExtra("WpiszNotatke", "Edit")
        intent.putExtra("TytułNotatki", note.noteTitle)
        intent.putExtra("TreśćNotatki", note.noteDescription)
        intent.putExtra("notatkaId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        Widok.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Usunięto", Toast.LENGTH_LONG).show()
    }
}