package com.example.bewell.Utils

import com.example.bewell.R
import com.example.bewell.common.Screens

data class BottomNavItems(
    val route: String,
    val title: String ,
    val icon: Int = 0
)

data class FitnessExercises(
    val id: Int,
    val name: String,
    val image: Int,
)

object Utils {

    val bottomNavigationItems = listOf(
        BottomNavItems(Screens.HOME.name, "Home", R.drawable.home_icon),
        BottomNavItems(Screens.FITNESS.name, "Fitness", R.drawable.fitness_icon),
        BottomNavItems(Screens.PROFILE.name, "Profile", R.drawable.profile_icon),
    )

    val weightLossExercises  = listOf(
        FitnessExercises(1, "Jumping Jacks", R.drawable.wl_10),
        FitnessExercises(1, "Burpees", R.drawable.wl_9),
        FitnessExercises(1, "Mountain Climbers", R.drawable.wl_8),
        FitnessExercises(1, "High Knees\n" + "\n", R.drawable.wl_7),
        FitnessExercises(1, "Jump Rope\n" + "\n", R.drawable.wl_6),
        FitnessExercises(1, "Skaters", R.drawable.wl_5),
        FitnessExercises(1, "Running (Outdoor or Treadmill)\n" + "\n", R.drawable.wl_4),
        FitnessExercises(1, "Jump Squats\n" + "\n", R.drawable.wl_3),
        FitnessExercises(1, "Fast-Paced Walking\n" + "\n", R.drawable.wl_2),
        FitnessExercises(1, "Bicycle Crunches\n" + "\n", R.drawable.wl_1),
    )

    val muscleBuildExercises  = listOf(
        FitnessExercises(1, "Push-ups", R.drawable.mb_10),
        FitnessExercises(1, "Pull-ups", R.drawable.mb_9),
        FitnessExercises(1, "Squats (Bodyweight/Weighted)", R.drawable.mb_8),
        FitnessExercises(1, "Deadlifts", R.drawable.mb_7),
        FitnessExercises(1, "Bench Press", R.drawable.mb_6),
        FitnessExercises(1, "Shoulder Press", R.drawable.mb_5),
        FitnessExercises(1, "Bent-over Rows", R.drawable.mb_4),
        FitnessExercises(1, "Bicep Curls", R.drawable.mb_3),
        FitnessExercises(1, "Tricep Dips", R.drawable.mb_2),
        FitnessExercises(1, "Plank-to-Push-up", R.drawable.mb_1),
    )

    val balancedTrainingExercises  = listOf(
        FitnessExercises(1, "Plank", R.drawable.bt_10),
        FitnessExercises(1, "Lunges", R.drawable.bt_9),
        FitnessExercises(1, "Glute Bridges", R.drawable.bt_8),
        FitnessExercises(1, "Russian Twists", R.drawable.bt_7),
        FitnessExercises(1, "Wall Sit", R.drawable.bt_6),
        FitnessExercises(1, "Yoga: Downward Dog", R.drawable.bt_5),
        FitnessExercises(1, "Bird Dog", R.drawable.bt_4),
        FitnessExercises(1, "Superman Hold", R.drawable.bt_3),
        FitnessExercises(1, "Dumbbell Squat-to-Press", R.drawable.bt_2),
        FitnessExercises(1, "Side Plank", R.drawable.bt_1),
    )

}

