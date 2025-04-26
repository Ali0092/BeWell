package com.example.bewell.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.bewell.R
import com.example.bewell.nav_components.Screens
import com.example.bewell.datastore.DataStoreManager
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.primaryColor
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dataStore: DataStoreManager = get(),
) {

    val coroutineScope = rememberCoroutineScope()

    Surface(color = backgroundColor, modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(
                start = 16.sdp, end = 16.sdp, top = 24.sdp, bottom = 16.sdp
            )
        ) {
            Column {
                Text(
                    text = "Welcome to BeWell",
                    fontSize = 28.textSdp,
                    color = darkBlueColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Track fitness, sleep, heart rate, and more...",
                    fontSize = 11.textSdp,
                    color = primaryColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.sdp))
                Text(
                    text = "Monitor your daily steps, workouts, sleep patterns, heart rate, and overall health. Get personalized insights and tips to maintain a balanced lifestyle and achieve your wellness goals.",
                    fontSize = 14.textSdp,
                    color = darkBlueColor,
                    modifier = Modifier.alpha(0.7f),
                    fontWeight = FontWeight.SemiBold
                )
                Image(
                    painter = painterResource(R.drawable.place_holder_for_onboarding),
                    contentDescription = "Onboarding Image",
                    modifier = Modifier.weight(1f)
                )

            }

            ElevatedButton(
                colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor),
                onClick = {
                    coroutineScope.launch {
                        dataStore.saveBooleanPref(DataStoreManager.ON_BOARDING_DONE_KEY, true)
                    }
                    navController.navigate(Screens.CREATE_PROFILE.name)
                },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.sdp, vertical = 4.sdp),
                    text = "Continue",
                    color = Color.White,
                    fontSize = 14.textSdp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}
