package com.example.mystudy

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.db.MyWord

class NoteAdapter(noteList: List<MyWord>) :
    RecyclerView.Adapter<NoteAdapter.CustomViewHolder>() {

    var noteList = noteList

    private lateinit var mclick : MyClick

    interface MyClick{
        fun onLongclick(myWord: MyWord)
    }

    fun setmclick(clickListener : MyClick){
        mclick = clickListener
    }

    init {

    }

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
        holder.itemView.setOnLongClickListener{
            mclick.onLongclick(noteList[position])
            notifyItemRemoved(position)
            return@setOnLongClickListener true
        }

    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val word: TextView = itemView.findViewById(R.id.wordview)
        val mean: TextView = itemView.findViewById(R.id.meanview)
    }


}
