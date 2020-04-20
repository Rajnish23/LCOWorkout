package com.rajnish.mobileapps.lcoworkoutapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rajnish.mobileapps.lcoworkoutapp.db.entity.DayWiseEntity
import java.util.*

@Dao
interface LCODao {


    @Insert
    suspend fun insertDayWithExercise(dayWiseEntityList: List<DayWiseEntity>)

    @Query("SELECT * FROM DayWise WHERE week_day_exercise in (SELECT DISTINCT week_day_exercise FROM DayWise) ORDER BY RANDOM() LIMIT 5")
    fun getRandomExercise() : LiveData<MutableList<DayWiseEntity>>

    @Query("SELECT * FROM DayWise WHERE weekDay=:day")
    fun getDayExercise(day : Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)): LiveData<MutableList<DayWiseEntity>>

}