package com.example.bewell.presentation.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat
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

/*
* Camera permission for AR....
*/

@Composable
fun CameraPermissionHandler(
    onPermissionGranted: @Composable () -> Unit
) {
    val context = LocalContext.current
    var showRationale by remember { mutableStateOf(false) }

    val permission = Manifest.permission.CAMERA
    val permissionState = checkPermissionState(context, permission)

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            showRationale = true
        }
    }

    when {
        permissionState -> {
            onPermissionGranted()
        }
        showRationale -> {
            PermissionRationaleDialog(
                onDismiss = { showRationale = false },
                onConfirm = {
                    showRationale = false
                    permissionLauncher.launch(permission)
                }
            )
        }
        else -> {
            SideEffect {
                permissionLauncher.launch(permission)
            }
        }
    }
}

@Composable
fun PermissionRationaleDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Camera Permission Required") },
        text = { Text("Camera access is required for AR functionality. Please grant the permission to continue.") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Request Permission")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

fun checkPermissionState(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

