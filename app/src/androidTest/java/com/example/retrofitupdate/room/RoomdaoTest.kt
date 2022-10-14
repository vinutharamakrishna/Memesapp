package com.example.retrofitupdate.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.retrofitupdate.model.Meme
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
@SmallTest
class RoomdaoTest  {
    private lateinit var database: MemeDatabase
    private lateinit var dao: Roomdao
    @Before
    fun setup(){
        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>() ,
            MemeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao= database.memeDao()!!
    }
    @After
    @Throws(IOException::class)
    fun teardown(){
        database.close()
    }
    @Test
    fun insertMeme()= runBlocking {
        val memeItem=
            Meme(2,1200,"181913649","Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val memeItem1=Meme(2,1200,"181913649","Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem1)
        dao.insertmemes(listMeme)
        val getMemeItems=dao. getMemes()
        val byName=dao. getMemes()
        assert(byName.size == getMemeItems.size)
    }
    @Test
    fun getAllMemes()= runBlocking {
        val memeItem=Meme(2,1200,"181913649","Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val memeItem1=Meme(2,1200,"181913649","Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem1)
        dao.insertmemes(listMeme)
        val totalItems=dao.getMemes()
        assert(totalItems.isNotEmpty())
    }
    @Test
    fun deleteMemes()= runBlocking {
        val memeItem=Meme(2,1200,"181913649","Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val memeItem1=Meme(2,1200,"181913649","Drake Hotline Bling","https://i.imgflip/30b1gx.jpg",1200 )
        val listMeme:List<Meme> = mutableListOf(memeItem,memeItem1)
        dao.delete(memeItem1)
        assert(listMeme.contains(memeItem1))
    }
}

