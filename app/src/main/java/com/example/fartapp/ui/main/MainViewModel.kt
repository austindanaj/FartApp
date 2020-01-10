package com.example.fartapp.ui.main

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fartapp.R
import com.example.fartapp.ui.SharedRepository

class MainViewModel : ViewModel() {

       fun getFartSoundId():LiveData<Int>{
        return SharedRepository.instance.fartId
    }

}
