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

    fun cast(data : BaseWord) : MyWord = MyWord(data.id,data.word,data.mean)
    fun cast(data: BaseWord , id : Int) = MyWord(id,data.word,data.mean)

}

