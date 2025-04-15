package com.example.bewell.presentation.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.bewell.R
import com.example.bewell.common.CircularProgressBar
import com.example.bewell.common.LinearProgressBar
import com.example.bewell.common.OutlinedEditTextField
import com.example.bewell.common.formatDateFromMillis
import com.example.bewell.common.getDateDifferences
import com.example.bewell.common.getDayOfMonthFromTimestamp
import com.example.bewell.common.valueInThreeDecimalPoints
import com.example.bewell.presentation.viewmodel.UserProfileViewModel
import com.example.bewell.presentation.viewstates.UserProfileState
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
import org.koin.androidx.compose.get

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserProfileViewModel = get(),
) {

    val userData = userViewModel.userProfileData.collectAsState(initial = UserProfileState()).value
    var goalProgress = remember { mutableStateOf(0f) }
    var monthlyTotalSteps = userViewModel.totalStepsEver.collectAsState().value
    var monthlyAchievedSteps = userViewModel.totalStepsEverDid.collectAsState().value

    var currentBarSize by remember { mutableStateOf(250f) } //current bar size
    val collapseProgress by remember(currentBarSize) {  //collapse progress from 0-1f
        derivedStateOf {
            (250f - currentBarSize) / (250f - 60f)
        }
    }

    var showAdFoodDialog by remember { mutableStateOf(false) }
    var showAddWaterDialog by remember { mutableStateOf(false) }
    var showAdSleepDialog by remember { mutableStateOf(false) }

    var dialogTitle by remember { mutableStateOf("") }
    var dialogSubTitle by remember { mutableStateOf("") }
    var dialogEtLable by remember { mutableStateOf("") }

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

    LaunchedEffect(userData) {

        if (userData.userProfile?.stepsGoal != null) {

            Log.d("checkingOutTheDifferenceBetweenDates", "HomeScreen: ${userData.userProfile.date.formatDateFromMillis()}")


//            userData.userProfile.date.getDateDifferences().forEach { it->
//                userViewModel.insertNewDay(it)
//                Log.d("checkingOutTheDifferenceBetweenDates", "difference: ${it.formatDateFromMillis()}")
//            }

//            userViewModel.userProfileData.value.userProfile!!.date = 1744614000000
//            userViewModel.updateUserData()

            goalProgress.value =
                (userData.userProfile!!.totalStepsDid.toFloat() + userData.userProfile.totalCaloriesBurned!!.toFloat()) / (userData.userProfile.stepsGoal!!.toFloat() + userData.userProfile.caloriesBurnedTarget!!.toFloat())
        }
    }

    /*
    * 1. Get user profile data and inflate all data on screen
    * 2. Update all data on screen
    * 3. Fill the gap dates
    * */

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
                Box(
                    modifier
                        .fillMaxSize()
                        .padding(vertical = 16.sdp)
                ) {
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
                                            text = userData.userProfile?.date?.getDayOfMonthFromTimestamp()
                                                .toString(),
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
                                            text = userData.userProfile?.totalStepsDid.toString() + " /",
                                            fontSize = 24.textSdp,
                                            color = darkBlueColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.width(4.sdp))
                                        Text(
                                            text = "${userData.userProfile?.stepsGoal} steps",
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
                                            text = userData.userProfile?.totalCaloriesBurned.toString() + " /",
                                            fontSize = 24.textSdp,
                                            color = darkBlueColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.width(4.sdp))
                                        Text(
                                            text = "${userData.userProfile?.caloriesBurnedTarget} kcal",
                                            fontSize = 14.textSdp,
                                            color = darkPurpleColor,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }

                                }
                                Spacer(modifier.weight(1f))
                                CircularProgressBar(
                                    modifier = Modifier.padding(16.sdp),
                                    progress = goalProgress.value,
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
                            color = lightPurpleColor,
                            shape = RoundedCornerShape(16.sdp),
                            tonalElevation = 1.sdp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.sdp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "This Month`s Goal",
                                    fontSize = 24.textSdp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )


                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = monthlyAchievedSteps.toString(),
                                        fontSize = 42.textSdp,
                                        color = darkPurpleColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.sdp))
                                    Text(
                                        text = "/ ${monthlyTotalSteps} steps",
                                        fontSize = 14.textSdp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
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
                            progressIndicatorText = if (userData.userProfile != null) ((userData.userProfile?.totalCaloriesIntake!!.toFloat() / userData.userProfile.caloriesIntake!!.toFloat()) * 100f).valueInThreeDecimalPoints()
                                .toString() + "%" else "0 %",
                            progress = if (userData.userProfile != null) ((userData.userProfile?.totalCaloriesIntake!!.toFloat() / userData.userProfile.caloriesIntake!!.toFloat()) * 100f) else 0f,
                            onButtonClick = {
                                showAdFoodDialog = true
                                dialogTitle = "Add Food"
                                dialogSubTitle = "Add Consumed calories"
                                dialogEtLable = "Enter food"
                            })//Food
                        Spacer(modifier = Modifier.height(16.sdp))
                        HomeScreenRoutineItem(
                            icon = R.drawable.water_icon,
                            title = "Water",
                            buttonText = "Add Water",
                            progressBackgroundColor = lightPurpleColor,
                            progressColor = darkPurpleColor,
                            progressIndicatorTextColor = darkPurpleColor,
                            progressIndicatorText = if (userData.userProfile != null) ((userData.userProfile?.totalWaterIntake!!.toFloat() / userData.userProfile.waterIntake!!.toFloat()) * 100f).valueInThreeDecimalPoints()
                                .toString() + " %" else "0 %",
                            progress = if (userData.userProfile != null) ((userData.userProfile?.totalWaterIntake!!.toFloat() / userData.userProfile.waterIntake!!.toFloat()) * 100f) else 0f,
                            onButtonClick = {
                                showAddWaterDialog = true
                                dialogTitle = "Add Water"
                                dialogSubTitle = "Add Consumed water in glasses"
                                dialogEtLable = "Water Glasses"
                            })//Water
                        Spacer(modifier = Modifier.height(16.sdp))
                        HomeScreenRoutineItem(
                            icon = R.drawable.sleep_icon,
                            title = "Sleep",
                            buttonText = "Add Sleep Time",
                            progressBackgroundColor = lightBlueColor,
                            progressColor = darkBlueColor,
                            progressIndicatorTextColor = darkBlueColor,
                            progressIndicatorText = if (userData.userProfile != null) ((userData.userProfile?.totalSleepOurs!!.toFloat() / userData.userProfile.sleepTime!!.toFloat()) * 100f).valueInThreeDecimalPoints()
                                .toString() + "%" else "0%",
                            progress = if (userData.userProfile != null) ((userData.userProfile?.totalSleepOurs!!.toFloat() / userData.userProfile.sleepTime!!.toFloat()) * 100f) else 0f,
                            isLastItem = true,
                            onButtonClick = {
                                showAdSleepDialog = true
                                dialogTitle = "Add Sleep time"
                                dialogSubTitle = "Add Sleep time in hours"
                                dialogEtLable = "Sleep time"
                            })//Sleep
                        Spacer(modifier = Modifier.height(16.sdp)) //temp
                    }
                }
            }
        }

    }


    AddSleepAndWaterDialog(
        showDialog = showAdFoodDialog,
        title = dialogTitle,
        subtitle = dialogSubTitle,
        etLabel = dialogEtLable,
        etPlaceHolder = dialogEtLable,
        onDismiss =  {
            showAdFoodDialog = false
        },
        add = { data->
            showAdFoodDialog = false
            userViewModel.userProfileData.value.userProfile!!.totalCaloriesIntake = userViewModel.userProfileData.value.userProfile!!.totalCaloriesIntake!!+data
            userViewModel.updateUserData()
        },
    )


    AddSleepAndWaterDialog(
        showDialog = showAddWaterDialog,
        title = dialogTitle,
        subtitle = dialogSubTitle,
        etLabel = dialogEtLable,
        etPlaceHolder = dialogEtLable,
        onDismiss =  {
            showAddWaterDialog = false
        },
        add = { data ->
            showAddWaterDialog = false
            userViewModel.userProfileData.value.userProfile!!.totalWaterIntake = userViewModel.userProfileData.value.userProfile!!.totalWaterIntake!!+data
            userViewModel.updateUserData()
        },
    )

    AddSleepAndWaterDialog(
        showDialog = showAdSleepDialog,
        title = dialogTitle,
        subtitle = dialogSubTitle,
        etLabel = dialogEtLable,
        etPlaceHolder = dialogEtLable,
        onDismiss =  {
            showAdSleepDialog = false
        },
        add = { data ->
            showAdSleepDialog = false
            userViewModel.userProfileData.value.userProfile!!.totalSleepOurs = userViewModel.userProfileData.value.userProfile!!.totalSleepOurs!!+data

            userViewModel.updateUserData()
        },
    )

}

@Composable
fun HomeScreenRoutineItem(
    icon: Int,
    title: String,
    buttonText: String,
    progress: Float,
    progressBackgroundColor: Color,
    progressColor: Color,
    progressIndicatorText: String,
    progressIndicatorTextColor: Color,
    progressIndicatorOffset: Offset = Offset(0f, 0f),
    isLastItem: Boolean = false,
    onButtonClick: () -> Unit = {},
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
                        onButtonClick()
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
                progress = progress,
                progressColor = progressColor,
                progressIndicatorTextColor = progressIndicatorTextColor
            )
        }
    }
}

@Composable
fun AddSleepAndWaterDialog(
    showDialog: Boolean,
    title: String,
    subtitle: String,
    etLabel: String,
    etPlaceHolder: String,
    onDismiss: () -> Unit,
    add: (Int) -> Unit,
) {

    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    if (showDialog) {
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(secondaryColor, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 16.sdp, horizontal = 16.sdp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkPurpleColor
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = subtitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = lightPurpleColor
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    OutlinedEditTextField(
                        label = etLabel,
                        placeHolder = etPlaceHolder,
                        isDarkPurpleColor = true,
                        action = KeyboardType.Number,
                        onValueChanged = { it ->
                            text = it
                        })

                    Spacer(Modifier.height(24.sdp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            modifier = Modifier.clickable {
                                onDismiss()
                            },
                            text = "Cancel",
                            fontSize = 14.textSdp,
                            color = lightPurpleColor,
                            fontWeight = FontWeight.Bold
                        )

                        Button(
                            onClick = {
                                if (text.isNotEmpty()) {
                                    add(text.toInt())
                                    onDismiss()
                                } else  {
                                    Toast.makeText(context, "Data must be filled ", Toast.LENGTH_SHORT).show()
                                }
                            },
                            modifier = Modifier.padding(start = 14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = darkPurpleColor)
                        ) {
                            Text(
                                text = "Add",
                                color = Color.White,
                                fontSize = 16.textSdp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}