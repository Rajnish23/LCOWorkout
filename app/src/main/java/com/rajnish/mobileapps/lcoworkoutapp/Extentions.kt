package com.rajnish.mobileapps.lcoworkoutapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

fun ImageView.loadGif(context: Context, resId : Int){
    Glide.with(context)
        .asGif()
        .load(resId)
        .into(this)
}

fun ImageView.loadImage(context: Context, resId: Int){
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context)
        .load(resId)
        .placeholder(circularProgressDrawable)
        .into(this)
}

private val NOTIFICATION_ID = 100
fun NotificationManager.sendNotification(channelId : String, applicationContext : Context){
    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            channelId,
            "LcoWorkout",
            NotificationManager.IMPORTANCE_HIGH
        )

        createNotificationChannel(notificationChannel)

        val builder = NotificationCompat.Builder(
            applicationContext, channelId
        ).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("LCO Workout")
            setContentText("It's time of day to Work out!!")
        }

        notify(NOTIFICATION_ID, builder.build())

    }
}

fun NotificationManager.cancel(){
    cancelAll()
}