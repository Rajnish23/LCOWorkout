package com.rajnish.mobileapps.lcoworkoutapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DayWise")
data class DayWiseEntity(

    @ColumnInfo(name = "weekDay")
    val day : Int,

    @ColumnInfo(name = "week_day_exercise")
    val exercisesName : String,

    @ColumnInfo(name = "gif_resource_id")
    val gifResourceId: Int = 0,

    @PrimaryKey(autoGenerate = true)
    val dayId : Int = 0
)