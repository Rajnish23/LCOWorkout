package com.rajnish.mobileapps.lcoworkoutapp

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences

    companion object {
        private val SHARED_PREF_NAME: String = "lco_sf"
        private val SHARED_PREF_IS_INITIALIZED: String = "is_initialized"
        private val SHARED_PREF_DAY_DONE : String = "day_done"
    }

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getIsInitialized() : Boolean{
        return sharedPreferences.getBoolean(SHARED_PREF_IS_INITIALIZED, false)
    }

    fun setInitialized(){
        sharedPreferences.edit()
            .putBoolean(SHARED_PREF_IS_INITIALIZED, true)
            .apply()
    }

    fun setDoneExerciseForDay(value: Boolean){
        sharedPreferences.edit()
            .putBoolean(SHARED_PREF_DAY_DONE, value)
            .apply()
    }

    fun getDoneExerciseForDay() : Boolean{
        return sharedPreferences.getBoolean(SHARED_PREF_DAY_DONE, false)
    }


}