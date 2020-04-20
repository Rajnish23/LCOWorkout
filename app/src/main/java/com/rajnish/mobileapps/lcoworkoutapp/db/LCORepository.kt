package com.rajnish.mobileapps.lcoworkoutapp.db

import androidx.lifecycle.LiveData
import com.rajnish.mobileapps.lcoworkoutapp.db.entity.DayWiseEntity

class LCORepository(private val lcoDao : LCODao) {



    fun  getRandomWorkOut() : LiveData<MutableList<DayWiseEntity>> = lcoDao.getRandomExercise()

    suspend fun insertDayWithExercise(dayWiseEntityList : List<DayWiseEntity>) = lcoDao.insertDayWithExercise(dayWiseEntityList)


    fun getDayWorkout() : LiveData<MutableList<DayWiseEntity>> = lcoDao.getDayExercise()


}