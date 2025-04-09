package com.example.bewell.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.bewell.R
import com.example.bewell.common.Screens
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.lightGreenColor
import com.example.bewell.ui.theme.secondaryColor

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        item {
            Text(
                text = "My Page",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.sdp),
                color = darkBlueColor,
                fontSize = 21.textSdp,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            BasicProfileCard()
        }
    }
}

@Composable
fun BasicProfileCard() {

    Box(modifier = Modifier.fillMaxWidth().padding(start = 8.sdp, end = 8.sdp, top = 16.sdp, bottom = 16.sdp)) {

        Surface( modifier = Modifier
            .fillMaxWidth()
            .height(170.sdp)
            .padding(top = 60.sdp, start = 8.sdp, end = 8.sdp, bottom = 4.sdp),
            color = secondaryColor,
            shape = RoundedCornerShape(16.sdp),
            shadowElevation = 1.sdp) {
            Column(
                modifier = Modifier.fillMaxSize().padding(top = 24.sdp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "The Great Ghibilli",
                    color = darkBlueColor,
                    fontSize = 18.textSdp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        Card(
            modifier = Modifier.align(Alignment.TopEnd).padding(end = 16.sdp, top = 70.sdp),
            colors = CardDefaults.cardColors(containerColor = darkBlueColor),
            shape = CircleShape
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.sdp, vertical = 4.sdp),
                text = "Edit",
                color = Color.White,
                fontSize = 10.textSdp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Image(painter = painterResource(R.drawable.profile_picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.sdp).align(Alignment.TopCenter).clip(CircleShape)
        )

    }

}
