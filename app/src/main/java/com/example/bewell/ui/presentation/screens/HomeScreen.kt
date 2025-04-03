package com.example.bewell.ui.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.darkPurpleColor

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().background(backgroundColor)) {

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

        Card( modifier = Modifier.fillMaxWidth().height(150.sdp) ) {



        }

    }
}
