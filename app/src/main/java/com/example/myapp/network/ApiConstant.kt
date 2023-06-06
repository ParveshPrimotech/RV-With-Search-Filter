package com.example.myapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConstant {

    var retrofitService: RetrofitService? = null
    fun getInstance(): RetrofitService {
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)
        }
        return retrofitService!!
    }
}