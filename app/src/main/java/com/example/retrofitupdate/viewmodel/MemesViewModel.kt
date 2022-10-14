package com.example.retrofitupdate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitupdate.model.Jokes
import com.example.retrofitupdate.model.Meme
import com.example.retrofitupdate.repository.MemesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemesViewModel(private val memesRepository: MemesRepository):ViewModel() {
    fun deleteMeme(meme : Meme) = viewModelScope.launch {
        memesRepository.deleteMeme(meme)
    }

    init {
        viewModelScope.launch (Dispatchers.IO){
            memesRepository.getMemes()
        }
    }

    val memes:LiveData<Jokes>
    get()=memesRepository.memes
}