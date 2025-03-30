package com.example.bewell.Utils

import com.example.bewell.R
import com.example.bewell.common.Screens

data class BottomNavItems(
    val route: String,
    val title: String ,
    val icon: Int = 0
)

object Utils {

    val bottomNavigationItems = listOf(
        BottomNavItems(Screens.HOME.name, "Home", R.drawable.home_icon),
        BottomNavItems(Screens.FITNESS.name, "Fitness", R.drawable.fitness_icon),
        BottomNavItems(Screens.PROFILE.name, "Profile", R.drawable.profile_icon),
    )

}