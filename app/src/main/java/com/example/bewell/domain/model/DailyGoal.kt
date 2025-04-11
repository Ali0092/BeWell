package com.example.bewell.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_goal")
data class DailyGoal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = System.currentTimeMillis(),
    var date: Long = 0,
    var stepsGoal: Int = 0,
    var stepsAchieved: Int = 0,
    var caloriesIntakeTake: Int = 0,
    var totalCaloriesIntake: Int = 0,
    var caloriesBurnedTarget: Int = 0,
    var caloriesBurned: Int = 0,
    var waterIntakeTarget: Int = 0,
    var waterIntakeAchieved: Int = 0,
    var sleepTimeTarget: Int = 0,
    var sleepTimeAchieved: Int = 0
)
