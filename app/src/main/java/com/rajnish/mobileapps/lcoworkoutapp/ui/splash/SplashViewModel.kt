package com.rajnish.mobileapps.lcoworkoutapp.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rajnish.mobileapps.lcoworkoutapp.LcoDatabaseParcer
import com.rajnish.mobileapps.lcoworkoutapp.db.LCOGymDatabase
import com.rajnish.mobileapps.lcoworkoutapp.db.LCORepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

class SplashViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: LCORepository

    init {
        val lcoDao = LCOGymDatabase.getInstance(application).getDao()
        repository = LCORepository(lcoDao)
    }


    fun insertAllData() {

        val dayWiseList = LcoDatabaseParcer.dayWiseExercise
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertDayWithExercise(dayWiseList)
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                repository.deleteAllWorkout()
            }
        }
    }
}