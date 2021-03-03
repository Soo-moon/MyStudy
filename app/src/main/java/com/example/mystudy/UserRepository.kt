package com.example.mystudy

import android.app.Application
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.annotation.RequiresApi
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
    private var tts: TextToSpeech? = null

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(RetrofitService::class.java)
        getWebData()
        tts = TextToSpeech(application) {
            if (it != TextToSpeech.ERROR) {
                tts!!.setPitch(0.6F)
            }
        }
    }

    private fun getWebData() {
        service.PostRequest().enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                Log.d("test", "in response")

                CoroutineScope(Dispatchers.IO).launch {
                    if (response.body()!!.version != db.getVersion()) {
                        for (i in response.body()!!.data.indices) {
                            db.wordDao().insert(response.body()!!.data[i])
                        }
                        db.setVersion(response.body()!!.version)
                        Log.d("test", db.getVersion().toString())
                    }
                }
            }
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("test", "error retrofit")
            }

        })
    }

    fun getList(): List<BaseWord> = db.wordDao().getAll()
    fun getData(index: Int): BaseWord = db.wordDao().getData(index)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun speak(str: String) {
        tts!!.speak(str, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun add(data: BaseWord) {
        CoroutineScope(Dispatchers.IO).launch {
            db.myWordDao().insert(data.cast(data))
        }

    }
}
