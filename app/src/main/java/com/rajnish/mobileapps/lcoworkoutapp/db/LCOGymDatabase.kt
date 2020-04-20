package com.rajnish.mobileapps.lcoworkoutapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rajnish.mobileapps.lcoworkoutapp.db.entity.DayWiseEntity

@Database(
    entities = [
        DayWiseEntity::class
    ], version = 2,
    exportSchema = false
)
abstract class LCOGymDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): LCOGymDatabase {

            return Room.databaseBuilder(
                context,
                LCOGymDatabase::class.java, "LCOGym.db"
            ).build()
        }

    }

    abstract fun getDao(): LCODao
}