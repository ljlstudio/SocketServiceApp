package com.rayvison.socketserviceapp.server

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import com.rayvison.socketserviceapp.server.LocalBinder
import android.content.Intent
import android.os.Build
import android.os.IBinder
import com.rayvison.socketserviceapp.R

class ConnectServer : Service() {
    private val binder = LocalBinder(this)
    private val notificationId = "keepapplifeid"
    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
//        super.onCreate()
//        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        //创建NotificationChannel
//        val channel =
//            NotificationChannel(
//                notificationId,
//                "keepapplifename",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//        notificationManager.createNotificationChannel(channel)
//        startForeground(1, notification)
    }

    private val notification: Notification
        get() {
            val builder = Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher) //
                .setContentTitle("App").setContentText("后台运行中")
            builder.setChannelId(notificationId)
            return builder.build()
        }
}