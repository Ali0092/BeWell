package com.example.bewell.presentation.screens

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRationale() {
    Box(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            Modifier.padding(vertical = 40.sdp, horizontal = 16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.permission_icon),
                modifier = Modifier.size(120.sdp),
                contentDescription = null
            )

            Spacer(Modifier.height(18.sdp))

            Text(
                "Activity Recognizer Permission Required",
                fontWeight = FontWeight.Bold,
                fontSize = 24.textSdp,
                color = darkBlueColor,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.height(18.sdp))

            Text(
                "This permission allows the app to detect and understand your physical activities, such as walking, running, cycling, or driving. It helps enhance user experience by enabling features like step counting, activity tracking, and intelligent behavior-based responses. Your activity data is processed securely and used solely to provide personalized and context-aware functionality within the app.",
                fontWeight = FontWeight.Medium,
                fontSize = 16.textSdp,
                color = darkBlueColor,
                modifier = Modifier.alpha(0.7f),
                textAlign = TextAlign.Start
            )
        }
        val context = LocalContext.current

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor), onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.fromParts("package", context.packageName, null)
                startActivity(context, intent, null)
            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.sdp)

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
