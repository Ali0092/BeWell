package com.example.bewell.presentation.screens

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
fun CreateUserProfileScreen(navController: NavHostController) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var height by remember { mutableStateOf("0") }
    var weight by remember { mutableStateOf("0") }



    Box(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        Column(modifier = Modifier.padding(horizontal = 12.sdp, vertical = 24.sdp).background(backgroundColor)) {

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

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text("Full name") },
                placeholder = { Text("Full name") },
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
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = age,
                onValueChange = {
                    age = it
                },
                label = { Text("Age") },
                placeholder = { Text("Age") },
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
                    keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = gender,
                onValueChange = {
                    gender = it
                },
                label = { Text("Gender") },
                placeholder = { Text("Gender") },
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
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                )
            )
            Spacer(Modifier.height(12.sdp))

            OutlinedTextField(
                value = height.toString(),
                onValueChange = {
                    height = it
                },
                label = { Text("Height") },
                placeholder = { Text("Height(cm)") },
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
                value = weight.toString(),
                onValueChange = {
                    weight= it
                },
                label = { Text("Weight") },
                placeholder = { Text("Weight(kg)") },
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
                navController.navigate(Screens.SETUP_GOAL.name)
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