package com.example.retrofitupdate

import android.app.Application
import com.example.retrofitupdate.api.ApiInterface
import com.example.retrofitupdate.api.apiUtility
import com.example.retrofitupdate.repository.MemesRepository
import com.example.retrofitupdate.room.MemeDatabase

class MyApplication:Application() {

    lateinit var memesRepository:MemesRepository
    override fun onCreate() {

        super.onCreate()
        val apiInterface= apiUtility.getInstance().create(ApiInterface::class.java)
      val database=MemeDatabase.getDatabase(applicationContext)
        memesRepository=MemesRepository(apiInterface,database,applicationContext)
    }

}