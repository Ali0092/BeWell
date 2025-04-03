package com.example.bewell.ui.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bewell.Utils.Utils
import com.example.bewell.common.BottomNavGraph
import com.example.bewell.ui.theme.darkBlueColor
import com.example.bewell.ui.theme.secondaryColor

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(topBar = {}, bottomBar = {
        BottomNavigationBar(navController)
    }, content = { innerPadding ->
        BottomNavGraph(
            modifier = Modifier.padding(innerPadding), navController = navController
        )
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