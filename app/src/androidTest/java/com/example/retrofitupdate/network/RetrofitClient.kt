package com.example.retrofitupdate.network

import com.example.retrofitupdate.api.apiUtility
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(apiUtility.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}