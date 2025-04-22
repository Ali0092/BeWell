package com.example.bewell.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.bewell.R
import com.example.bewell.common.Utils.selectedExercise
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor

@Composable
fun ViewFitnessExercise(modifier: Modifier = Modifier, navController : NavHostController) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 12.sdp, vertical = 8.sdp)) {
            //TopBar
            Row(modifier = Modifier.fillMaxWidth().height(66.sdp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Rounded.ArrowBack,contentDescription = null, tint = darkBlueColor,
                    modifier = Modifier.clickable{
                        navController.navigateUp()
                    })
                Spacer(modifier = Modifier.width(8.sdp))
                Text(
                    text ="Exercise Details",
                    fontSize = 16.textSdp,
                    color = darkBlueColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ar_view),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.sdp)
                        .clickable {

                    }
                )
            }
            //Body
            Spacer(modifier = Modifier.height(8.sdp))
            Image(
                painter = painterResource(selectedExercise.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.sdp)
                    .clip(RoundedCornerShape(12.sdp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.sdp))
            Text(
                text = selectedExercise.name,
                fontSize = 18.textSdp,
                color = darkBlueColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.sdp))
            Text(
                text = selectedExercise.description,
                fontSize = 15.textSdp,
                color = darkBlueColor,
            )
        }

    }

}