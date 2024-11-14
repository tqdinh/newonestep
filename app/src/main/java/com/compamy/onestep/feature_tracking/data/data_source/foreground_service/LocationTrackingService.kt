package com.compamy.onestep.feature_tracking.data.data_source.foreground_service

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.compamy.onestep.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class LocationTrackingService : Service() {
    companion object {
        private const val CHANNEL_ID = "locationchannel"
        private const val NOTIFICATION_ID = 1
        private const val ACTION_STOP_SERVICE = "STOP_SERVICE"
    }

    private val _elapsedTime = MutableStateFlow(0L)
    val elapsedTime: StateFlow<Long> = _elapsedTime

    private val _currentJourneyId: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentJourneyId : StateFlow<String?> = _currentJourneyId

    private val _currentLocation: MutableStateFlow<LocationState?> = MutableStateFlow(null)
    val currentLocation: StateFlow<LocationState?> = _currentLocation

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback


    var serviceJob = Job()
    val binder = TrackingBinder()

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onCreate() {
        super.onCreate()


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Setup location callback
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    _currentLocation.value = LocationState(location.latitude, location.longitude)
                    // Use location data
                    Log.d(
                        "LOCATION_CHANGE",
                        "Lat: ${location.latitude}, Lng: ${location.longitude}"
                    )
                }
            }
        }

        requestLocationUpdates()

        _currentJourneyId.value = UUID.randomUUID().toString()

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

    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            stopSelf()
            return
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 2000 // Every 10 seconds
            fastestInterval = 500 // Fastest every 5 seconds
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, mainLooper)
    }


    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
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
                while (true) {
                    delay(1000)
                    _elapsedTime.value += 1
                }
            }
        }
    }

    fun stopForeground() {
        // stopForeground()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }


    inner class TrackingBinder : Binder() {
        fun getService() = this@LocationTrackingService
    }

    override fun onDestroy() {
        super.onDestroy()
        _currentJourneyId.value = null

        serviceJob.cancel()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}