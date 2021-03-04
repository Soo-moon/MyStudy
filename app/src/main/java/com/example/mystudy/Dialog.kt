package com.example.mystudy

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.loader.AssetsProvider
import android.graphics.Typeface
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.mystudy.db.MyWord
import java.lang.ClassCastException


class Dialog : DialogFragment() {

    private lateinit var listener: DialogListener

    interface DialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, myWord: MyWord)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }


    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val view =inflater.inflate(R.layout.dialog,null)
            val wordEdit =view.findViewById<EditText>(R.id.wordEdit)
            val meanEdit = view.findViewById<EditText>(R.id.meanEdit)


            builder.setMessage(R.string.Dialogtittle)

            builder.setView(view)
            builder.setPositiveButton(R.string.add){
                    dialogInterface: DialogInterface, i: Int -> listener.onDialogPositiveClick(this,
                MyWord(wordEdit.text.toString() , meanEdit.text.toString()))
            }
            builder.setNegativeButton(R.string.cancel
            ) { dialog, id ->
                listener.onDialogNegativeClick(this)
            }


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException()
        }

    }
}