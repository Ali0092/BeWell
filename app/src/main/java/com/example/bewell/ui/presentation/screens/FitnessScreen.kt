package com.example.bewell.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bewell.R
import com.example.bewell.Utils.FitnessExercises
import com.example.bewell.Utils.Utils
import com.example.bewell.Utils.Utils.balancedTrainingExercises
import com.example.bewell.Utils.Utils.muscleBuildExercises
import com.example.bewell.ui.sdp
import com.example.bewell.ui.textSdp
import com.example.bewell.ui.theme.backgroundColor
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.lightBlueColor
import com.example.bewell.ui.theme.secondaryColor

@Composable
fun FitnessScreen(modifier: Modifier = Modifier) {

    var currentBarSize by remember { mutableFloatStateOf(250f) }
    // Calculate transition progress (0.0 = expanded, 1.0 = collapsed)
    val collapseProgress = remember(currentBarSize) {
        // Map 250f-60f range to 0f-1f
        (250f - currentBarSize) / (250f - 60f)
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
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(bottom = 54.sdp)
            .nestedScroll(nestedScrollConnection)
    ) {
        //column
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(currentBarSize.toFloat().dp)
                .padding(horizontal = 16.sdp)
        ) {
            // Calculate the horizontal offset for the text
            // When expanded (collapseProgress = 0), offset is 0 (centered)
            // When collapsed (collapseProgress = 1), offset moves text to start
            val screenWidth = LocalConfiguration.current.screenWidthDp
            val maxOffset = (screenWidth / 2) - 48  // Rough estimate for centered to start position
            val horizontalOffset = maxOffset * collapseProgress

            val fontSize = 36 - (10 * collapseProgress)

            Text(
                text = "Fitness",
                color = darkBlueColor,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = (-horizontalOffset).dp)
            )
        }

        //list....
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(horizontal = 8.sdp)
                .offset(0.sdp, currentBarSize.toFloat().dp)
        ) {
            item {
                FitnessScreenBar(title = "Weight Loss", image = R.drawable.fitness1, dataList = Utils.weightLossExercises)
            }
            item {
                FitnessScreenBar(title = "Build Muscle", image = R.drawable.fitness2, dataList = muscleBuildExercises)
            }
            item {
                FitnessScreenBar(title = "Balanced Training", image = R.drawable.fitness3, dataList = balancedTrainingExercises)
            }
        }
    }

}

@Composable
fun FitnessScreenBar(modifier: Modifier = Modifier, title: String, image: Int, dataList: List<FitnessExercises>) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.sdp, end = 8.sdp, bottom = 12.sdp),
        color = secondaryColor,
        shape = RoundedCornerShape(16.sdp),
        shadowElevation = 1.sdp
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.sdp)
                .fillMaxSize()
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.sdp, top = 12.sdp, bottom = 8.sdp),
                color = darkBlueColor,
                fontSize = 16.textSdp,
                fontWeight = FontWeight.Bold
            )

            LazyRow(modifier = Modifier.fillMaxWidth().padding(end = 6.sdp)) {
                items(10) { index->
                    FitnessBarSingleItem(title = dataList[index].name, img = dataList[index].image)
                }
            }

        }
    }
}

@Composable
fun FitnessBarSingleItem(title:String,img: Int) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(140.sdp)
            .padding(start = 12.sdp, top = 8.sdp)
    ) {
        Image(
            painter = painterResource(img),
            contentDescription = null,
            modifier = Modifier
                .size(140.sdp)
                .clip(RoundedCornerShape(12.sdp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.sdp))
        Text(
            text = title,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            color = darkBlueColor,
            fontSize = 14.textSdp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            minLines = 2
        )
    }

}