package com.example.bewell.ui.presentation.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bewell.R
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.darkPurpleColor
import com.example.bewell.ui.theme.lightBlueColor
import com.example.bewell.ui.theme.secondaryColor
import com.example.bewell.ui.viewmodel.StepsCounterViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: StepsCounterViewModel = getViewModel()) {

    val steps = viewModel.counter.collectAsState().value
    val calories = viewModel.calories.collectAsState().value

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

        Spacer(modifier = Modifier.height(16.sdp))

        //daily card
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp),
            color = secondaryColor,
            shape = RoundedCornerShape(16.sdp),
            shadowElevation = 2.sdp
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 16.sdp, horizontal = 12.sdp),
                    verticalArrangement = Arrangement.Center
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

        //weekly card
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.sdp)
                .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp),
            color = secondaryColor,
            shape = RoundedCornerShape(16.sdp),
            shadowElevation = 2.sdp
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
                        fontWeight = FontWeight.Medium
                    )
                }
                LinearProgressBar(modifier = Modifier.weight(1f), text = "20%")
            }
        }

    }
}

@Composable
fun LinearProgressBar(modifier: Modifier = Modifier, text: String) {

    val textMeasurer = rememberTextMeasurer()


    val style = TextStyle(
        fontSize = 14.textSdp,
        color = darkPurpleColor,
        fontWeight = FontWeight.Bold,
        background = lightBlueColor
    )

    val textLayoutResult = remember(text, style) {
        textMeasurer.measure(text, style)
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier
            .fillMaxHeight()
            .width(100.sdp)) {

            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = style,
                topLeft = Offset(
                    x = 50f,
                    y = size.height/2-45f,
                )
            )

            //background progress indicator
            drawLine(
                color = lightBlueColor,
                start = Offset(0f, size.height / 2),
                end = Offset(size.width, size.height / 2),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
            //foreground progress indicator
            drawLine(
                color = darkBlueColor,
                start = Offset(0f, size.height / 2),
                end = Offset(50f, size.height / 2),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
        }
    }

}

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float, // Between 0f and 1f
    size: Dp = 100.dp,
    strokeWidth: Dp = 10.dp,
    animate: Boolean = true,
    content: (@Composable () -> Unit)? = null,
) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing),
        label = "circularProgressAnim"
    )

    Box(
        modifier = modifier
            .size(size)
            .padding(strokeWidth / 2), contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val sweepAngle = animatedProgress.value * 360

            // Draw background circle
            drawArc(
                color = lightBlueColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            // Draw progress arc
            drawArc(
                color = darkBlueColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

        }

        Text(
            text = animatedProgress.value.toString() + " %",
            fontSize = 18.textSdp,
            color = darkPurpleColor,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )

        // Center content (optional)
        content?.let {
            it()
        }
    }
}