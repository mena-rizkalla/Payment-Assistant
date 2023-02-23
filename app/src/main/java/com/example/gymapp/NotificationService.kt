package com.example.gymapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationService(private val  context: Context) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(userName : String){

        val intent = Intent(context,MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(context , 1 ,intent ,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Payment done")
            .setContentText("Payment done by ${userName}")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(activityPendingIntent)
            .build()
        notificationManager.notify(1,notification)
    }


    companion object{
        const val CHANNEL_ID = "gym_channel"
    }
}