package com.example.bewell.ui.presentation.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import com.example.bewell.R
import com.example.bewell.common.BottomNavGraph
import com.example.bewell.common.Screens
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.lightBlueColor
import com.example.bewell.ui.theme.secondaryColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.sdp)
                .background(backgroundColor)
        ) {

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "BeWell",
                fontSize = 28.textSdp,
                color = darkBlueColor,
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = "Star",
                tint = darkBlueColor,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.sdp, bottom = 16.sdp)
            )

        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.sdp)
                .padding(16.sdp),
            color = secondaryColor,
            shape = RoundedCornerShape(16.sdp),
            shadowElevation = 2.sdp
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Steps 0",
                    fontSize = 30.textSdp,
                    color = darkBlueColor,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRationale() {
    Box(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(Modifier.padding(vertical = 40.sdp, horizontal = 16.sdp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(R.drawable.permission_icon), modifier = Modifier.size(100.sdp),contentDescription = null)
            Spacer(Modifier.height(12.sdp))
            Text("Activity Recognizer Permission Required", fontWeight = FontWeight.Bold, fontSize = 24.textSdp, color = darkBlueColor, textAlign = TextAlign.Center)
            Spacer(Modifier.height(18.sdp))
            Text("This permission allows the app to detect and understand your physical activities, such as walking, running, cycling, or driving. It helps enhance user experience by enabling features like step counting, activity tracking, and intelligent behavior-based responses. Your activity data is processed securely and used solely to provide personalized and context-aware functionality within the app.",
                fontWeight = FontWeight.Medium,
                fontSize = 18.textSdp,
                color = darkBlueColor,
                modifier = Modifier.alpha(0.7f),
                textAlign = TextAlign.Center
            )
        }
        val context = LocalContext.current

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor),
            onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                startActivity(context,intent, null)
            },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.sdp)


        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.sdp, vertical = 4.sdp),
                text = "Go to settings",
                color = Color.White,
                fontSize = 14.textSdp,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}
