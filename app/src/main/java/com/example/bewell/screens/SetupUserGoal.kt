package com.example.bewell.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.bewell.common.OutlinedEditTextField
import com.example.bewell.nav_components.Screens
import com.example.bewell.common.Utils.checkIfCanMoveToNext
import com.example.bewell.datastore.DataStoreManager
import com.example.bewell.viewmodel.UserProfileViewModel
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.primaryColor
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun SetupUserGoal(
    navController: NavHostController,
    viewModel: UserProfileViewModel = get(),
    dataStore: DataStoreManager = get(),
) {

    val coroutineScope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.sdp, vertical = 24.sdp)
                .background(backgroundColor)
        ) {

            Text(
                text = "Setup Daily Goal",
                fontSize = 28.textSdp,
                color = darkBlueColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.sdp))
            Text(
                text = "Define your ideal steps, sleep, calories, and hydration targets.",
                fontSize = 14.textSdp,
                color = primaryColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(24.sdp))

            OutlinedEditTextField(
                label = "Steps Goal",
                placeHolder = "Steps (per day)",
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    viewModel.createUserProfileData.value.stepsGoal = it.toInt()
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Calories intake",
                placeHolder = "Calories intake (per day)",
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.caloriesIntake = it.toInt()
                    }
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Calories burn",
                placeHolder = "Calories burn (per day)",
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.caloriesBurnedTarget = it.toInt()
                    }
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Water intake",
                placeHolder = "Water (glasses per day)",
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.waterIntake = it.toInt()
                    }
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Sleep time",
                placeHolder = "Sleep(hours per day)",
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.sleepTime = it.toInt()
                    }
                })

        }

        val context = LocalContext.current

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor), onClick = {
                checkIfCanMoveToNext(screen = 1, userData = viewModel.createUserProfileData.value) { canMove, error ->
                    if (canMove) {
                        coroutineScope.launch {
                            dataStore.saveBooleanPref(DataStoreManager.USER_PROFILE_DONE_KEY, true)
                        }
                        viewModel.createUserProfile()
                        navController.navigate(Screens.MAIN.name)
                    } else {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.sdp, bottom = 16.sdp)
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