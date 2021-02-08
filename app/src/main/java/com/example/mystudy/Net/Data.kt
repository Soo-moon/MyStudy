package com.example.mystudy.Net

import androidx.databinding.BaseObservable
import com.example.mystudy.db.BaseWord
import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("version") var version : String ="", @SerializedName("WordData")var data : List<BaseWord>) : BaseObservable()