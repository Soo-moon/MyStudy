package com.example.mystudy.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.Dialog
import com.example.mystudy.NoteAdapter
import com.example.mystudy.R
import com.example.mystudy.db.MyWord
import com.example.mystudy.db.WordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.ClassCastException


class MyNote : AppCompatActivity(), Dialog.DialogListener, NoteAdapter.MyClick {

    private val db: WordDatabase? = WordDatabase.getInstance(this)
    lateinit var recycle: RecyclerView
    private var noteAdapter : NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_note)

        recycle = findViewById(R.id.recycle)


        db!!.myWordDao().getAll().observe(this , Observer {
            noteAdapter = NoteAdapter(it)
            noteAdapter!!.setmclick(this)
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

    override fun onLongclick(myWord: MyWord) {
        Toast.makeText(this ,"삭제하였습니다." ,Toast.LENGTH_SHORT).show()
        CoroutineScope(Dispatchers.IO).launch {
            db!!.myWordDao().delete(myWord)
        }
    }




}

