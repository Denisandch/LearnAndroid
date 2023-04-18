package com.example.test

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private val _list = MutableLiveData<MutableList<Int>>()
    val list: LiveData<MutableList<Int>> = _list


    fun init() {
        _list.value = mutableListOf(1,3,4,1,3,4)
    }
    fun listUpdate() {
        val num = _list.value?.size?.plus(1) ?: 0


        Log.i("tsd", num.toString())
        _list.value?.add(num)
        _list.postValue(_list.value)

    }
}