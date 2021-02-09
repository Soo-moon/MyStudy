package com.example.mystudy

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mystudy.Net.Data
import com.example.mystudy.Net.RetrofitService
import com.example.mystudy.db.BaseWord
import com.example.mystudy.db.WordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserRepository(application: Application) {


    private val db: WordDatabase = WordDatabase.getInstance(application)!!


    private var service: RetrofitService


    init {
        Log.d("test", "repo init start")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(RetrofitService::class.java)
        Log.d("test", "repo init end")
    }


    fun getData(index: Int): LiveData<BaseWord> {

        val data = MutableLiveData<BaseWord>()

        service.PostRequest().enqueue(object : Callback<Data> {
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("test", "error retrofit")
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.body()!!.version != )


                data.value = response.body()!!.data.get(index)
                Log.d("test", index.toString())
                Log.d("test", data.value.toString())
            }
        })
        return data

    }


    fun DataUpdate(response: Response<Data>) {


        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..(response.body()!!.data.size) - 1) {
                db.wordDao().insert(response.body()!!.data.get(i))
            }
        }
    }

}
