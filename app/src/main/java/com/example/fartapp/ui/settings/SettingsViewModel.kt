package com.example.fartapp.ui.settings

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fartapp.R
import com.example.fartapp.ui.SharedRepository

class SettingsViewModel : ViewModel() {

    fun getFartSoundId():Int{
        return SharedRepository.instance.fartId.value!!
    }

    fun getFartSoundId(pos:Int):Int{
        return SharedRepository.fartList[pos]
    }

    fun setFartId(id:Int){
        SharedRepository.instance.setFartId(id)
    }

    fun getFartNames():LiveData<List<String>>{
        return SharedRepository.instance.fartNames
    }


}
