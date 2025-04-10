package com.example.bewell.ui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.bewell.common.Screens
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.lightBlueColor
import com.example.bewell.ui.theme.primaryColor

//UserProfile (store once)
//- id
//- name
//- age
//- gender
//- height
//- weight

@Composable
fun SetupUserGoal(navController: NavHostController) {

    var stepsPerDayGoal by remember { mutableStateOf("0") }
    var caloriesIntake by remember { mutableStateOf("0") }
    var caloriesBurn by remember { mutableStateOf("0") }
    var sleepTime by remember { mutableStateOf("0") }
    var waterGlasses by remember { mutableStateOf("0") }
    

    Box(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        Column(modifier = Modifier.padding(horizontal = 12.sdp, vertical = 24.sdp).background(backgroundColor)) {

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


            OutlinedTextField(
                value = stepsPerDayGoal,
                onValueChange = {
                    stepsPerDayGoal= it
                },
                label = { Text("Steps Goal") },
                placeholder = { Text("Steps (per day)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkBlueColor,     // Border color when focused
                    unfocusedBorderColor = lightBlueColor, // Border color when not focused
                    cursorColor = darkBlueColor,          // Cursor color
                    focusedLabelColor = darkBlueColor,
                    unfocusedLabelColor = lightBlueColor,
                    focusedTextColor = darkBlueColor,
                    unfocusedTextColor = darkBlueColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = caloriesIntake,
                onValueChange = {
                    caloriesIntake = it
                },
                label = { Text("Calories intake") },
                placeholder = { Text("Calories intake (per day)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkBlueColor,     // Border color when focused
                    unfocusedBorderColor = lightBlueColor, // Border color when not focused
                    cursorColor = darkBlueColor,          // Cursor color
                    focusedLabelColor = darkBlueColor,
                    unfocusedLabelColor = lightBlueColor,
                    focusedTextColor = darkBlueColor,
                    unfocusedTextColor = darkBlueColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = caloriesBurn.toString(),
                onValueChange = {
                    caloriesBurn= it
                },
                label = { Text("Calories burn") },
                placeholder = { Text("Calories burn (per day)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkBlueColor,     // Border color when focused
                    unfocusedBorderColor = lightBlueColor, // Border color when not focused
                    cursorColor = darkBlueColor,          // Cursor color
                    focusedLabelColor = darkBlueColor,
                    unfocusedLabelColor = lightBlueColor,
                    focusedTextColor = darkBlueColor,
                    unfocusedTextColor = darkBlueColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = waterGlasses,
                onValueChange = {
                    waterGlasses= it
                },
                label = { Text("Water intake") },
                placeholder = { Text("Water (glasses per day)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkBlueColor,     // Border color when focused
                    unfocusedBorderColor = lightBlueColor, // Border color when not focused
                    cursorColor = darkBlueColor,          // Cursor color
                    focusedLabelColor = darkBlueColor,
                    unfocusedLabelColor = lightBlueColor,
                    focusedTextColor = darkBlueColor,
                    unfocusedTextColor = darkBlueColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = sleepTime,
                onValueChange = {
                    sleepTime = it
                },
                label = { Text("Sleep time") },
                placeholder = { Text("Sleep(hours per day)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkBlueColor,     // Border color when focused
                    unfocusedBorderColor = lightBlueColor, // Border color when not focused
                    cursorColor = darkBlueColor,          // Cursor color
                    focusedLabelColor = darkBlueColor,
                    unfocusedLabelColor = lightBlueColor,
                    focusedTextColor = darkBlueColor,
                    unfocusedTextColor = darkBlueColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                )
            )

        }

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor),
            onClick = {
                navController.navigate(Screens.MAIN.name)
            }, modifier = Modifier.align(Alignment.BottomEnd).padding(end = 16.sdp, bottom = 16.sdp)) {
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