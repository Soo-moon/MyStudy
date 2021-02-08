package com.example.mystudy

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.mystudy.db.BaseWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)



    var index: Int = 0
    var data : LiveData<BaseWord>

    init {
        data = repository.getData(index)
    }

    fun onNextClick() {

    }

    fun onBackClick() {

    }


}


