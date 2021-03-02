package com.example.mystudy.db

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyWord (
    @PrimaryKey
    var id: Int,
    var word: String,
    var mean: String
) : BaseObservable()