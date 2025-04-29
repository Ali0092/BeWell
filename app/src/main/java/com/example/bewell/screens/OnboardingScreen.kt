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
import androidx.compose.ui.res.stringResource
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
        Box(modifier = Modifier.padding(horizontal = 12.sdp, vertical = 24.sdp)) {
            Column {
                Text(
                    text = stringResource(R.string.welcome_to_bewell),
                    fontSize = 28.textSdp,
                    color = darkBlueColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.track_fitness_sleep_heart_rate_and_more),
                    fontSize = 11.textSdp,
                    color = primaryColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.sdp))
                Text(
                    text = stringResource(R.string.monitor_your_daily_steps_workouts_sleep_patterns_heart_rate_and_overall_health_get_personalized_insights_and_tips_to_maintain_a_balanced_lifestyle_and_achieve_your_wellness_goals),
                    fontSize = 14.textSdp,
                    color = darkBlueColor,
                    modifier = Modifier.alpha(0.7f),
                    fontWeight = FontWeight.Normal
                )
                Image(
                    painter = painterResource(R.drawable.place_holder_for_onboarding),
                    contentDescription = null,
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
                    text = stringResource(R.string.continue_),
                    color = Color.White,
                    fontSize = 14.textSdp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

    }
}
