package com.compamy.onestep.feature_tracking.data.data_source.foreground_service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.compamy.onestep.R

class LocationTrackingService : Service() {

    companion object {
        private const val ACTION_STOP = "STOP_SERVICE"
        private val NOTIFICATION_ID = 1
        private val CHANNEL_ID = "tracking_channel_id"
    }


    private val binder = TrackingBinder()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            CHANNEL_ID,
            "location tracking",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        getSystemService(NotificationManager::class.java)?.let {
            it.createNotificationChannel(channel)
        }
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (ACTION_STOP == intent?.action) {
            stopForeground(STOP_FOREGROUND_REMOVE)

            stopSelf()
            return START_NOT_STICKY
        }
        startForeground(NOTIFICATION_ID, createNotification())
        return START_STICKY
    }

    fun createNotification(): Notification {
        val stopIntent =
            Intent(this@LocationTrackingService, LocationTrackingService::class.java).apply {
                action = ACTION_STOP
            }
        val stopPending = PendingIntent.getService(
            this@LocationTrackingService,
            0,
            stopIntent,
            PendingIntent.FLAG_MUTABLE
        )

        return NotificationCompat.Builder(this@LocationTrackingService, CHANNEL_ID)
            .setContentTitle("Running")
            .setSmallIcon(R.drawable.app_nav_map)
            .addAction(R.drawable.ic_warning,"stop",stopPending)
            .build()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }


    inner class TrackingBinder : Binder() {
        fun getService(): LocationTrackingService = this@LocationTrackingService
    }
}