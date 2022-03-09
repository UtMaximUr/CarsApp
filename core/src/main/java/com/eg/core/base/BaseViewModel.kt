package com.eg.cars.main.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {

    open val progressState: LiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    open val errorState: LiveData<String> by lazy {
        MutableLiveData()
    }
}