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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.bewell.R
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
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.sdp, vertical = 24.sdp)
            .background(backgroundColor)
    ) {
        Column {

            Text(
                text = stringResource(R.string.create_user_profile),
                fontSize = 28.textSdp,
                color = darkBlueColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(8.sdp))
            Text(
                text = stringResource(R.string.we_need_your_information_to_help_you_out_to_achieve_your_goals_more_easily),
                fontSize = 14.textSdp,
                color = darkBlueColor,
                modifier = Modifier.alpha(0.7f),
                fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.height(24.sdp))

            OutlinedEditTextField(
                label = stringResource(R.string.full_name),
                placeHolder = stringResource(R.string.full_name),
                action = KeyboardType.Text,
                onValueChanged = { it ->
                    viewModel.createUserProfileData.value.name = it
                })

            Spacer(Modifier.height(8.sdp))

            OutlinedEditTextField(
                label = stringResource(R.string.age),
                placeHolder = stringResource(R.string.age),
                action = KeyboardType.Phone,
                onValueChanged = { it ->
                    viewModel.createUserProfileData.value.age = it.toInt()
                })

            Spacer(Modifier.height(8.sdp))

            OutlinedEditTextField(
                label = stringResource(R.string.gender),
                placeHolder = stringResource(R.string.gender),
                action = KeyboardType.Text,
                onValueChanged = { it ->
                    viewModel.createUserProfileData.value.gender = it
                })

            Spacer(Modifier.height(8.sdp))

            OutlinedEditTextField(
                label = stringResource(R.string.height),
                placeHolder = stringResource(R.string.height_cm),
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.height = it.toDouble()
                    }
                })

            Spacer(Modifier.height(8.sdp))

            OutlinedEditTextField(
                label = stringResource(R.string.weight),
                placeHolder = stringResource(R.string.weight_kg),
                action = KeyboardType.Number,
                onValueChanged = { it ->
                    if (it.isNotEmpty()) {
                        viewModel.createUserProfileData.value.weight = it.toDouble()
                    }
                })

        }


        ElevatedButton(
            colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor), onClick = {
                checkIfCanMoveToNext(userData = viewModel.createUserProfileData.value) { canMove, error ->
                    if (canMove) {
                        navController.navigate(Screens.SETUP_GOAL.name)
                    } else {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }
            }, modifier = Modifier.align(Alignment.BottomEnd)
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
