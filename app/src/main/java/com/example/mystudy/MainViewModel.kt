package com.example.mystudy

import android.app.Application
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mystudy.db.BaseWord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository = UserRepository(application)

    var index: Int = 1
    val data = MutableLiveData<BaseWord>()


    init {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                data.postValue(repository.getData(index))
            }
        }
    }


    fun onNextClick() {
        CoroutineScope(Dispatchers.IO).launch {
            if(index < repository.getList().size-1){
                index++
                data.postValue(repository.getData(index))
            }
            else{
                index = 1
                data.postValue(repository.getData(index))
            }
        }
    }

    fun onBackClick() {
        CoroutineScope(Dispatchers.IO).launch {
            if(index > 1){
                index--
                data.postValue(repository.getData(index))
            }
            else{
                index = repository.getList().size
                data.postValue(repository.getData(index))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun voice(){
        repository.speak(data.value!!.word)
    }

    fun add(){
        repository.add(data.value!!)
        Toast.makeText(getApplication(),"단어를 추가했습니다.",Toast.LENGTH_SHORT).show()
    }


}


