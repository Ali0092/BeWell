package com.example.bewell.presentation.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bewell.R
import com.example.bewell.common.CircularProgressBar
import com.example.bewell.common.LinearProgressBar
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.darkGreenColor
import com.example.bewell.ui.theme.darkPurpleColor
import com.example.bewell.ui.theme.lightBlueColor
import com.example.bewell.ui.theme.lightGreenColor
import com.example.bewell.ui.theme.lightPurpleColor
import com.example.bewell.ui.theme.secondaryColor
import com.example.bewell.presentation.viewmodel.StepsCounterViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: StepsCounterViewModel = getViewModel()) {

    val steps = viewModel.counter.collectAsState().value
    val calories = viewModel.calories.collectAsState().value

    var currentBarSize by remember { mutableStateOf(250f) } //current bar size
    val collapseProgress by remember(currentBarSize) {  //collapse progress from 0-1f
        derivedStateOf {
            (250f - currentBarSize) / (250f - 60f)
        }
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override suspend fun onPostFling(
                consumed: Velocity,
                available: Velocity,
            ): Velocity {
                return super.onPostFling(consumed, available)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                return super.onPostScroll(consumed, available, source)
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                return super.onPreFling(available)
            }

            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                val delta = available.y
                val previousBarSize = currentBarSize
                val newBarSize = currentBarSize + delta.dp.value.toFloat()

                currentBarSize = newBarSize.coerceIn(60f, 250f)
                val consumed = currentBarSize - previousBarSize

                return Offset(0f, consumed)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .nestedScroll(nestedScrollConnection)
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(currentBarSize.toFloat().dp)
                .background(backgroundColor)
        ) {

            val screenWidth = LocalConfiguration.current.screenWidthDp
            val maxOffset = (screenWidth / 2) - 48  // Rough estimate for centered to start position
            val horizontalOffset = maxOffset * collapseProgress

            val fontSize = 36 - (10 * collapseProgress)

            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = (-horizontalOffset).dp),
                text = "BeWell",
                fontSize = fontSize.sp,
                color = darkBlueColor,
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = "Star",
                tint = darkBlueColor,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 24.sdp, bottom = 16.sdp)
            )

        }

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(bottom = 60.sdp)
                .offset(0.sdp, currentBarSize.toFloat().dp)
        ) {
            item {
                Box(modifier
                    .fillMaxSize()
                    .padding(vertical = 16.sdp)) {
                    Column {
                        //daily card
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.sdp),
                            color = secondaryColor,
                            shape = RoundedCornerShape(16.sdp),
                            shadowElevation = 1.sdp
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Column(
                                    modifier = Modifier.padding(
                                        vertical = 16.sdp, horizontal = 12.sdp
                                    ), verticalArrangement = Arrangement.Center
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(R.drawable.day),
                                            contentDescription = null,
                                            modifier = Modifier.size(25.sdp)
                                        )
                                        Spacer(modifier = Modifier.width(8.sdp))
                                        Text(
                                            text = "1",
                                            fontSize = 24.textSdp,
                                            color = darkBlueColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.width(4.sdp))
                                        Text(
                                            text = "day",
                                            fontSize = 14.textSdp,
                                            color = darkPurpleColor,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.sdp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(R.drawable.footprint),
                                            contentDescription = null,
                                            modifier = Modifier.size(25.sdp)
                                        )
                                        Spacer(modifier = Modifier.width(8.sdp))
                                        Text(
                                            text = "${steps}",
                                            fontSize = 24.textSdp,
                                            color = darkBlueColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.width(4.sdp))
                                        Text(
                                            text = "steps",
                                            fontSize = 14.textSdp,
                                            color = darkPurpleColor,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.sdp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(R.drawable.calories),
                                            contentDescription = null,
                                            modifier = Modifier.size(25.sdp)
                                        )
                                        Spacer(modifier = Modifier.width(8.sdp))
                                        Text(
                                            text = "${calories}",
                                            fontSize = 24.textSdp,
                                            color = darkBlueColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.width(4.sdp))
                                        Text(
                                            text = "kcal",
                                            fontSize = 14.textSdp,
                                            color = darkPurpleColor,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }

                                }
                                Spacer(modifier.weight(1f))
                                CircularProgressBar(
                                    modifier = Modifier.padding(16.sdp),
                                    progress = 0.3f,
                                    size = 120.sdp,
                                    strokeWidth = 8.sdp
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(16.sdp))
                        //weekly card
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.sdp)
                                .padding(horizontal = 16.sdp),
                            color = secondaryColor,
                            shape = RoundedCornerShape(16.sdp),
                            shadowElevation = 1.sdp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.sdp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = "$steps",
                                        fontSize = 42.textSdp,
                                        color = darkBlueColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.sdp))
                                    Text(
                                        text = "/6,000 steps",
                                        fontSize = 14.textSdp,
                                        color = lightBlueColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                LinearProgressBar(
                                    modifier = Modifier.padding(16.sdp),
                                    text = "20%",
                                    progressBackgroundColor = lightBlueColor,
                                    progressColor = darkBlueColor,
                                    progressIndicatorTextColor = darkBlueColor
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.sdp))
                        //Food, Sleep,Water
                        HomeScreenRoutineItem(
                            icon = R.drawable.food_icon,
                            title = "Food",
                            buttonText = "Enter Food",
                            progressBackgroundColor = darkGreenColor,
                            progressColor = lightGreenColor,
                            progressIndicatorTextColor = lightGreenColor,
                            progressIndicatorText = "10%"
                        )//Food
                        Spacer(modifier = Modifier.height(16.sdp))
                        HomeScreenRoutineItem(
                            icon = R.drawable.water_icon,
                            title = "Water",
                            buttonText = "Add Water",
                            progressBackgroundColor = lightPurpleColor,
                            progressColor = darkPurpleColor,
                            progressIndicatorTextColor = darkPurpleColor,
                            progressIndicatorText = "20%"
                        )//Sleep
                        Spacer(modifier = Modifier.height(16.sdp))
                        HomeScreenRoutineItem(
                            icon = R.drawable.sleep_icon,
                            title = "Sleep",
                            buttonText = "Add Sleep Time",
                            progressBackgroundColor = lightBlueColor,
                            progressColor = darkBlueColor,
                            progressIndicatorTextColor = darkBlueColor,
                            progressIndicatorText = "Target",
                            isLastItem = true
                        )//Water
                        Spacer(modifier = Modifier.height(16.sdp)) //temp
                        HomeScreenRoutineItem(
                            icon = R.drawable.sleep_icon,
                            title = "Sleep",
                            buttonText = "Add Sleep Time",
                            progressBackgroundColor = lightBlueColor,
                            progressColor = darkBlueColor,
                            progressIndicatorTextColor = darkBlueColor,
                            progressIndicatorText = "Target",
                            isLastItem = true
                        )//Water
                    }
                }
            }
        }

    }


}

@Composable
fun HomeScreenRoutineItem(
    icon: Int,
    title: String,
    buttonText: String,
    progressBackgroundColor: Color,
    progressColor: Color,
    progressIndicatorText: String,
    progressIndicatorTextColor: Color,
    progressIndicatorOffset: Offset = Offset(0f, 0f),
    isLastItem: Boolean = false,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.sdp)
            .padding(start = 16.sdp, end = 16.sdp, bottom = if (isLastItem) 2.sdp else 0.sdp),
        color = secondaryColor,
        shape = RoundedCornerShape(16.sdp),
        shadowElevation = 1.sdp
    ) {
        Row(
            modifier = Modifier.padding(16.sdp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Row {
                    Image(
                        painter = painterResource(icon),
                        contentDescription = null,
                        modifier = Modifier.size(25.sdp)
                    )
                    Spacer(modifier = Modifier.width(8.sdp))
                    Text(
                        text = title,
                        fontSize = 17.textSdp,
                        color = darkBlueColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.sdp))
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(containerColor = darkBlueColor),
                    onClick = {

                    }) {
                    Text(
                        modifier = Modifier.padding(horizontal = 4.sdp, vertical = 2.sdp),
                        text = buttonText,
                        color = Color.White,
                        fontSize = 12.textSdp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            LinearProgressBar(
                modifier = Modifier.padding(16.sdp),
                text = progressIndicatorText,
                progressBackgroundColor = progressBackgroundColor,
                progressColor = progressColor,
                progressIndicatorTextColor = progressIndicatorTextColor
            )
        }
    }
}
