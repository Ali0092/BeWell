package com.example.bewell.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.example.bewell.R
import com.example.bewell.viewmodel.UserProfileViewModel
import com.example.bewell.viewstates.UserProfileState
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.lightBlueColor
import com.example.bewell.ui.theme.secondaryColor
import org.koin.androidx.compose.get

@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(R.drawable.inprogress), contentDescription = null, modifier = Modifier.size(300.sdp))
        Text(
            text = stringResource(R.string.in_progress),
            fontSize = 18.textSdp,
            color = darkBlueColor,
            fontWeight = FontWeight.Bold
        )
    }

}

//badges card
@Composable
fun BasicProfileCard(name: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.sdp, end = 8.sdp, bottom = 12.sdp)
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.sdp)
                .padding(start = 8.sdp, end = 8.sdp, bottom = 4.sdp),
            color = secondaryColor,
            shape = RoundedCornerShape(16.sdp),
            shadowElevation = 1.sdp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.sdp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    color = darkBlueColor,
                    fontSize = 18.textSdp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        Card(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.sdp, top = 70.sdp),
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

    }

}

@Composable
fun BadgesCard(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.sdp, end = 16.sdp, bottom = 16.sdp),
        color = secondaryColor,
        shape = RoundedCornerShape(16.sdp),
        shadowElevation = 1.sdp
    ) {

        Column( modifier = Modifier
            .fillMaxWidth()
            .height(200.sdp)
            .padding(12.sdp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.sdp)) {
                Text(
                    text = "Badges",
                    color = darkBlueColor,
                    fontSize = 16.textSdp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = null,
                    modifier = Modifier.size(25.sdp),
                    tint = darkBlueColor
                )
            }
            LazyRow {
                items(4) {
                    BadgesSingleItem()
                }
            }

        }

    }

}

@Composable
fun BadgesSingleItem(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(vertical = 8.sdp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.badge1), contentDescription = null, modifier = Modifier.size(120.sdp,70.sdp))
        Text(
            text = "10,000 steps",
            color = darkBlueColor,
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "12/08/2025",
            color = lightBlueColor,
            fontSize = 12.textSdp,
            fontWeight = FontWeight.Bold
        )
    }
}