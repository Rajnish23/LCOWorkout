package com.rajnish.mobileapps.lcoworkoutapp

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DailyBoradcastReciever : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("TAG",":Recieved ");
        PreferenceManager(context)
            .setDoneExerciseForDay(false)
        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        notificationManager.sendNotification(
            "lco",
            context.applicationContext
        )

    }
}
