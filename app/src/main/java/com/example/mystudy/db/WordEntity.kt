package com.example.mystudy.db

import androidx.databinding.BaseObservable
import androidx.room.*

@Entity
data class BaseWord(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var word: String,
    var mean: String
) : BaseObservable()

