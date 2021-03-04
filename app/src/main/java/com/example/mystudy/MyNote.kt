package com.example.mystudy

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.db.MyWord
import com.example.mystudy.db.WordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MyNote : AppCompatActivity(), Dialog.DialogListener {

    private val db: WordDatabase? = WordDatabase.getInstance(this)
    lateinit var recycle: RecyclerView
    private var noteAdapter : NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_note)

        recycle = findViewById(R.id.recycle)


        db!!.myWordDao().getAll().observe(this , Observer {
            noteAdapter = NoteAdapter(application , it)
            recycle.adapter = noteAdapter
        })

        recycle.layoutManager = LinearLayoutManager(application)


        val homeButton: ImageButton = findViewById(R.id.home)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val addButton: ImageButton = findViewById(R.id.add)
        addButton.setOnClickListener {
            val dialog = Dialog()
            dialog.show(supportFragmentManager, "string?")
        }




    }

    override fun onDialogPositiveClick(dialog: DialogFragment, myWord: MyWord) {
        CoroutineScope(Dispatchers.IO).launch {
            db!!.myWordDao().insert(myWord)
        }


    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }


}

