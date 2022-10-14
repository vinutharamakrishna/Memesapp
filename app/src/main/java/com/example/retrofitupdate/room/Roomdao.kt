package com.example.retrofitupdate.room

import androidx.room.*
import com.example.retrofitupdate.model.Meme

@Dao
interface Roomdao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertmemes(meme:List<Meme>)
    @Query("SELECT * FROM memes")
    suspend fun getMemes():List<Meme>
    @Delete
    suspend fun delete(meme: Meme)


}