package com.rajnish.mobileapps.lcoworkoutapp

import android.app.Application
import android.database.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlin.properties.ObservableProperty

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val toobarTitleObserver = MutableLiveData<String>()


}