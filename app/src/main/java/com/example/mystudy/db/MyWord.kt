package com.example.mystudy.db

import androidx.databinding.BaseObservable
import androidx.room.Entity

@Entity(primaryKeys = arrayOf("word", "mean"))

data class MyWord (
    var word: String,
    var mean: String
): BaseObservable()