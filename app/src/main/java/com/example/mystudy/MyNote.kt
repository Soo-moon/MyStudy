package com.example.mystudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MyNote : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_note)

        val recycle : RecyclerView = findViewById(R.id.recycle)
        recycle.adapter = NoteAdapter(application)
        recycle.layoutManager = LinearLayoutManager(application)




    }
}