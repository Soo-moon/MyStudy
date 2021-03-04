package com.example.mystudy

import android.app.Application
import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.db.MyWord
import com.example.mystudy.db.WordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteAdapter(application: Application , noteList: List<MyWord>) :
    RecyclerView.Adapter<NoteAdapter.CustomViewHolder>() {

    val db = WordDatabase.getInstance(application)
    var noteList = noteList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int = noteList.size


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.word.text = noteList[position].word
        holder.mean.text = noteList[position].mean
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val word: TextView = itemView.findViewById(R.id.wordview)
        val mean: TextView = itemView.findViewById(R.id.meanview)
    }

    fun setlist(noteList : List<MyWord>){
        this.noteList = noteList
        notifyDataSetChanged()
    }



}