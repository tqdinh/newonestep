package com.compamy.onestep

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ServiceTestRule
import com.compamy.onestep.feature_tracking.data.data_source.foreground_service.LocationTrackingService
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RobolectricTestService
{
    @get:Rule
    val serviceRule = ServiceTestRule()

    @Test
  //  @Throws(TimeoutException::class)
    fun testWithBoundService()
    {
        val serviceIntent = Intent(ApplicationProvider.getApplicationContext<Context>(),LocationTrackingService::class.java).apply {

        }


        val binder = serviceRule.bindService(serviceIntent)
        val service :LocationTrackingService = (binder as LocationTrackingService.TrackingBinder).getService()

        assertTrue(service!= null)

    }

    @Test
    fun testMainActivityLoadHomeFirst()
    {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

    }
}