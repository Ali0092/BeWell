package com.example.bewell.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var age: Int = 0,
    var gender: String = "",
    var height: Double = 0.0,
    var weight: Double = 0.0, //physical traits till here

    var stepsGoal: Int = 0,
    var caloriesIntake: Int = 0,
    var caloriesBurnedTarget: Int = 0,
    var waterIntake: Int = 0, //glasses
    var sleepTime: Int = 0, //hours

)