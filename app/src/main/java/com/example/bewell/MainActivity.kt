package com.example.bewell

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.bewell.nav_components.NavGraph
import com.example.bewell.nav_components.Screens
import com.example.bewell.datastore.DataStoreManager
import com.example.bewell.ui.theme.BeWellTheme
import com.example.bewell.viewmodel.SplashHandlingViewModel
import com.example.bewell.viewmodel.UserProfileViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity(), SensorEventListener {

    val sensorManager by lazy { getSystemService(SENSOR_SERVICE) as SensorManager }
    var sensor: Sensor? = null
    val viewModel: SplashHandlingViewModel by viewModel()
    val userProfileViewModel: UserProfileViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isSplashLoading.value //show until this is true
        }

        setContent {
            BeWellTheme {

                val dataStore: DataStoreManager = get()
                val navController = rememberNavController()

                userProfileViewModel

                val isOnBoardingDone = dataStore.getSavedBooleanPref(DataStoreManager.ON_BOARDING_DONE_KEY).collectAsState(initial = null)
                val isUserProfileDone = dataStore.getSavedBooleanPref(DataStoreManager.USER_PROFILE_DONE_KEY).collectAsState(initial = null)

                val startDestination = remember { mutableStateOf<String?>(null) }

                if (isOnBoardingDone.value!=null && isUserProfileDone.value!=null) {
                    startDestination.value = when {
                        isOnBoardingDone.value == true && isUserProfileDone.value == true -> Screens.MAIN.name
                        isOnBoardingDone.value == true -> Screens.CREATE_PROFILE.name
                        else -> Screens.ONBOARDING.name //its onboarding
                    }
                    viewModel.setSplashLoadingStatus(false)
                }

                if (startDestination.value==null) {
                    viewModel.setSplashLoadingStatus(true)
                }else {
                    NavGraph(
                        navController = navController,
                        startDestination = startDestination.value!!
                    )
                }
            }
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        sensorEvent?.let { event ->
            if (event.sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                if (sensorEvent.values.isNotEmpty() && sensorEvent.values[0] == 1.0f) {
                    userProfileViewModel.userProfileViewModel()
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
