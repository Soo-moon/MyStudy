package com.example.mystudy.Net

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/")
    fun PostRequest() : Call<Data>

}
