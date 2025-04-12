package com.example.bewell.presentation.screens

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bewell.Utils.Utils
import com.example.bewell.common.BottomNavGraph
import com.example.bewell.presentation.viewmodel.UserProfileViewModel
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.secondaryColor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.get

/*
*

DailyStats (per day)
- date (primary key)
- steps
- sleepHours
- waterGlasses
- caloriesFromSteps (calculated)
- caloriesFromSleep (optional)
- foodList: List<FoodItem>
- caloriesConsumed (sum of food calories)

FoodItem
- name
- amount
- calories
* */



@OptIn(ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainScreen(
    viewModel: UserProfileViewModel = get()
) {
    val navController = rememberNavController()
    val state = rememberPermissionState(
        Manifest.permission.ACTIVITY_RECOGNITIONad
    )

    val userProfileData = viewModel.userProfile.collectAsState().value

    Log.d("checkingouttheUserProfile", "MainScreen: ${userProfileData}")

    Scaffold(topBar = {}, bottomBar = {
        if(state.status.isGranted) {
            BottomNavigationBar(navController)
        }
    }, content = { innerPadding ->
        when{
            state.status.isGranted -> {
                BottomNavGraph(
                    modifier = Modifier.padding(innerPadding), navController = navController
                )
            }
            else -> {
                LaunchedEffect(Unit) {
                    state.launchPermissionRequest()
                }
                PermissionRationale()
            }
        }
    })
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = secondaryColor
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        Utils.bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route, onClick = {
                navController.navigate(item.route)
            }, icon = {
                Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
            }, label = {
                Text(text = item.title, fontWeight = FontWeight.Bold)
            }, alwaysShowLabel = false, colors = NavigationBarItemColors(
                selectedIconColor = Color.White,
                selectedTextColor = darkBlueColor,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                selectedIndicatorColor = darkBlueColor,
                disabledIconColor = Color.White,
                disabledTextColor = Color.White
            )
            )
        }
    }
}