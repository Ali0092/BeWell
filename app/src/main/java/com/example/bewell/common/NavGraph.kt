package com.example.bewell.common


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bewell.R
import com.example.bewell.presentation.screens.CreateUserProfileScreen
import com.example.bewell.presentation.screens.FitnessScreen
import com.example.bewell.presentation.screens.HomeScreen
import com.example.bewell.presentation.screens.MainScreen
import com.example.bewell.presentation.screens.OnboardingScreen
import com.example.bewell.presentation.screens.ProfileScreen
import com.example.bewell.presentation.screens.SetupUserGoal

//Navigation Routes List
enum class Screens {
    ONBOARDING,
    CREATE_PROFILE,
    SETUP_GOAL,
    MAIN,
    HOME,
    FITNESS,
    PROFILE
}

//Navigation
sealed class SimpleNavigation(val route: String) {
    class OnBoarding: SimpleNavigation(Screens.ONBOARDING.name)
    class CreateProfile: SimpleNavigation(Screens.CREATE_PROFILE.name)
    class SetupGoal: SimpleNavigation(Screens.SETUP_GOAL.name)
    class Main: SimpleNavigation(Screens.ONBOARDING.name)
}

sealed class BottomNavigationScreens(val route: String, val title: String, val icon: Int) {
    class Home: BottomNavigationScreens(Screens.HOME.name, "Home", R.drawable.home_icon)
    class Fitness: BottomNavigationScreens(Screens.FITNESS.name, "Fitness", R.drawable.fitness_icon)
    class Profile: BottomNavigationScreens(Screens.PROFILE.name, "Profile", R.drawable.profile_picture)
}

//NavGraph
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Screens.ONBOARDING.name) {
            OnboardingScreen(navController = navController)
        }
        composable(Screens.CREATE_PROFILE.name) {
            CreateUserProfileScreen(navController = navController)
        }
        composable(Screens.SETUP_GOAL.name) {
            SetupUserGoal(navController = navController)
        }
        composable(Screens.MAIN.name) {
            MainScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.HOME.name
    ) {

        //Bottom Navigation
        composable(Screens.HOME.name) {
            HomeScreen()
        }

        composable(Screens.FITNESS.name) {
            FitnessScreen()
        }

        composable(Screens.PROFILE.name) {
            ProfileScreen()
        }

    }
}