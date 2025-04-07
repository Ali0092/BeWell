package com.example.bewell

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.bewell.common.NavGraph
import com.example.bewell.common.Screens
import com.example.bewell.ui.theme.BeWellTheme
import com.example.bewell.ui.viewmodel.StepsCounterViewModel
import kotlinx.coroutines.flow.update
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity(), SensorEventListener {

    val sensorManager by lazy { getSystemService(SENSOR_SERVICE) as SensorManager }
    var sensor: Sensor? = null
    val viewModel: StepsCounterViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        setContent {
            BeWellTheme {
                NavGraph(
                    navController = rememberNavController(),
                    startDestination = Screens.ONBOARDING.name
                )
            }
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent?.let { event ->
            if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                if (sensorEvent.values.isNotEmpty() && sensorEvent.values[0] == 1.0f) {
                    viewModel.incrementCounter()
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {
        //
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

}
