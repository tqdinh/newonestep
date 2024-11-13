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
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.compamy.onestep.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

class LocationTrackingService : Service() {
    companion object {
        private const val CHANNEL_ID = "locationchannel"
        private const val NOTIFICATION_ID = 1
        private const val ACTION_STOP_SERVICE = "STOP_SERVICE"
    }

    var isRunning = false

    var currentJourneyId: String? = null

    fun setCurrentJouneyId(journeId: String) {
        currentJourneyId = journeId
    }

    var serviceJob = Job()
    val binder = TrackingBinder()

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onCreate() {
        super.onCreate()
        isRunning = true
        currentJourneyId =UUID.randomUUID().toString()
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Location tracking",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        getSystemService(NotificationManager::class.java)?.let {
            it.createNotificationChannel(channel)
        }
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        isRunning = true

        if (ACTION_STOP_SERVICE == intent?.action) {
            stopForeground()
            return START_NOT_STICKY
        } else {
            restartCount()
        }
        return START_STICKY
    }


    @SuppressLint("NewApi")
    fun createNotification(): Notification {

        val stopIntent =
            Intent(this@LocationTrackingService, LocationTrackingService::class.java).apply {
                action = ACTION_STOP_SERVICE
            }
        val stopPending = PendingIntent.getService(
            this@LocationTrackingService,
            0,
            stopIntent,
            PendingIntent.FLAG_MUTABLE
        )

        return NotificationCompat.Builder(this@LocationTrackingService, CHANNEL_ID)
            .setContentTitle("Running")
            .setSmallIcon(R.drawable.app_icon)
            .addAction(R.drawable.app_icon, "Stop", stopPending)
            .build()

    }

    fun restartCount() {
        if (serviceJob.isActive) {
            serviceJob.cancel()
            serviceJob = Job()
            CoroutineScope(Dispatchers.Default + serviceJob).launch {
                var i = 0
                while (true) {
                    i += 1
                    delay(1000)
                    Log.d("BGTHREAD", "${i}")
                }
            }
        }
    }

    fun stopForeground() {
        currentJourneyId=null
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()

    }


    inner class TrackingBinder : Binder() {
        fun getService() = this@LocationTrackingService
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }
}