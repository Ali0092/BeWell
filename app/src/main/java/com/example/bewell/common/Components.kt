package com.example.bewell.common

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.darkPurpleColor
import com.example.bewell.ui.theme.lightBlueColor

@Composable
fun OutlinedEditTextField(
    modifier: Modifier = Modifier,
    label: String,
    placeHolder: String,
    action: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onValueChanged: (String) -> Unit,
) {

    var textString by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textString,
        onValueChange = {
            textString = it
            onValueChanged(it)
        },
        label = { Text(label) },
        placeholder = { Text(placeHolder) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = darkBlueColor,     // Border color when focused
            unfocusedBorderColor = lightBlueColor, // Border color when not focused
            cursorColor = darkBlueColor,          // Cursor color
            focusedLabelColor = darkBlueColor,
            unfocusedLabelColor = lightBlueColor,
            focusedTextColor = darkBlueColor,
            unfocusedTextColor = darkBlueColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = action, imeAction = imeAction
        )
    )

}

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    text: String,
    progressBackgroundColor: Color,
    progressColor: Color,
    progressIndicatorTextColor: Color,
) {

    val textMeasurer = rememberTextMeasurer()

    val style = TextStyle(
        fontSize = 14.textSdp, color = progressIndicatorTextColor, fontWeight = FontWeight.Bold
    )

    remember(text, style) {
        textMeasurer.measure(text, style)
    }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(100.sdp), contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            drawText(
                textMeasurer = textMeasurer, text = text, style = style, topLeft = Offset(
                    x = 50f,
                    y = size.height / 2 - 50f,
                )
            )

            //background progress indicator
            drawLine(
                color = progressBackgroundColor,
                start = Offset(0f, size.height / 2),
                end = Offset(size.width, size.height / 2),
                strokeWidth = 15f,
                cap = StrokeCap.Round
            )
            //foreground progress indicator
            drawLine(
                color = progressColor,
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