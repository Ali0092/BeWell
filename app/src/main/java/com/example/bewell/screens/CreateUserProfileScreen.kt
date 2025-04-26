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
import com.example.bewell.viewmodel.UserProfileViewModel
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.primaryColor
import org.koin.androidx.compose.get

@Composable
fun CreateUserProfileScreen(
    navController: NavHostController,
    viewModel: UserProfileViewModel = get(),
) {

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
                text = "Create User Profile",
                fontSize = 28.textSdp,
                color = darkBlueColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.sdp))
            Text(
                text = "We Need your information to help you out to achieve your goals more easily !",
                fontSize = 14.textSdp,
                color = primaryColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(24.sdp))

            OutlinedEditTextField(
                label = "Full name",
                placeHolder = "Full name",
                action = KeyboardType.Text,
                onValueChanged = { it ->
                    viewModel.createUserProfileData.value.name = it
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Age",
                placeHolder = "Age",
                action = KeyboardType.Phone,
                onValueChanged = { it ->
                    viewModel.createUserProfileData.value.age = it.toInt()
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Gender",
                placeHolder = "Gender",
                action = KeyboardType.Text,
                onValueChanged = { it ->
                    viewModel.createUserProfileData.value.gender = it
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Height",
                placeHolder = "Height(cm)",
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.height = it.toDouble()
                    }
                })

            Spacer(Modifier.height(12.sdp))

            OutlinedEditTextField(
                label = "Weight",
                placeHolder = "Weight(kg)",
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.weight = it.toDouble()
                    }
                })

        }

        val context = LocalContext.current

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor), onClick = {
                checkIfCanMoveToNext(userData = viewModel.createUserProfileData.value) { canMove, error ->
                    if (canMove) {
                        navController.navigate(Screens.SETUP_GOAL.name)
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
