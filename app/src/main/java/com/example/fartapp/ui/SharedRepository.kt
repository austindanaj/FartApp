package com.example.fartapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fartapp.R

class SharedRepository private constructor() {


    private val _fartId = MutableLiveData<Int>().apply {
        value = R.raw.fart1
    }
    val fartId : LiveData<Int> = _fartId

    private val _fartNames = MutableLiveData<List<String>>().apply {
        value = listOf("Fart 1")
    }
    val fartNames : LiveData<List<String>> = _fartNames

    init{
        val fartName = ArrayList<String>(fartList.size)
        fartName.clear()

        var index = 1;
        for(fart in fartList){
            fartName.add("Fart $index")
            index++
        }
        _fartNames.postValue(fartName)
    }
    
    private object HOLDER {
        val INSTANCE = SharedRepository()
    }

    companion object {
        val instance: SharedRepository by lazy { HOLDER.INSTANCE }
        val fartList = arrayOf(R.raw.fart1, R.raw.fart2, R.raw.fart3, R.raw.fart4, R.raw.fart5, R.raw.fart6, R.raw.fart7, R.raw.fart8, R.raw.fart9, R.raw.fart10 )
    }

    fun setFartId(id:Int){
        _fartId.postValue(fartList[id])
    }



}