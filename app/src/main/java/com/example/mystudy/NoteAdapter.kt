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
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.db.MyWord
import com.example.mystudy.db.WordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteAdapter(application: Application) :
    RecyclerView.Adapter<NoteAdapter.CustomViewHolder>() {


    private val db : WordDatabase = WordDatabase.getInstance(application)!!
    private lateinit var noteList : List<MyWord>
    private var tts : TextToSpeech? =null

    init {
        tts = TextToSpeech(application){
            if (it != TextToSpeech.ERROR)
                tts!!.setPitch(0.6F)
        }

        CoroutineScope(Dispatchers.IO).launch {
            noteList = db.myWordDao().getAll()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.word.text = noteList[position].word
        holder.mean.text = noteList[position].mean
        holder.voice.setOnClickListener {
            tts!!.speak(noteList[position].word , TextToSpeech.QUEUE_FLUSH,null,null)
        }
    }




    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val word: TextView = itemView.findViewById(R.id.wordview)
        val mean: TextView = itemView.findViewById(R.id.meanview)
        val voice: ImageButton = itemView.findViewById(R.id.item_voice)
    }

}