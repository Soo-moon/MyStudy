package com.example.mystudy.db

import androidx.databinding.BaseObservable
import androidx.room.*

@Entity
data class BaseWord(
    @PrimaryKey
    var id: Int,
    var word: String,
    var mean: String
) : BaseObservable(){

    fun cast(data : BaseWord) : MyWord = MyWord(data.word,data.mean)

}

