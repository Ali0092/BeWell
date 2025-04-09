package com.example.bewell.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.bewell.R
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.lightBlueColor
import com.example.bewell.ui.theme.secondaryColor

@Composable
fun FitnessScreen(modifier: Modifier = Modifier) {

    Box {
        s
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        item {
            Text(
                text = "Fitness",
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
            FitnessScreenBar(title = "Weight Loss", image = R.drawable.fitness1)
        }
        item {
            FitnessScreenBar(title = "Build Muscle", image = R.drawable.fitness2)
        }
        item {
            FitnessScreenBar(title = "Balanced Training", image = R.drawable.fitness3)
        }
    }
}

@Composable
fun FitnessScreenBar(modifier: Modifier = Modifier, title:String, image: Int) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.sdp, end = 8.sdp, bottom = 16.sdp),
        color = secondaryColor,
        shape = RoundedCornerShape(16.sdp),
        shadowElevation = 1.sdp
    ) {
        Column(modifier = Modifier
            .padding(vertical = 12.sdp)
            .fillMaxSize()) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(16.sdp, bottom = 8.sdp),
                color = darkBlueColor,
                fontSize = 16.textSdp,
                fontWeight = FontWeight.Bold
            )

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(4) {
                    FitnessBarSingleItem(image)
                }
            }

        }
    }
}

@Composable
fun FitnessBarSingleItem(img: Int) {

    Column(modifier = Modifier.wrapContentHeight().width(140.sdp).padding(start = 12.sdp)) {
        Image(painter = painterResource(img),
            contentDescription = null,
            modifier = Modifier.size(140.sdp).clip(RoundedCornerShape(12.sdp)),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(4.sdp))
        Text(
            text = "Dumbbell exercise daily 4 times......",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(),
            color = darkBlueColor,
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )
        Text(
            text = "Fitness",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(),
            color = lightBlueColor,
            fontSize = 11.textSdp,
            fontWeight = FontWeight.Bold
        )
    }

}