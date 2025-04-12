package com.example.bewell.common

import com.example.bewell.domain.model.UserProfile

object Utils {


    fun checkIfCanMoveToNext(screen: Int = 0,userData: UserProfile, canMove: (Boolean, String) -> Unit) {

        if (screen==0) {
            if (userData.name == "") {
                canMove(false, "Please enter your name")
            }
            if (userData.age == 0) {
                canMove(false, "Please enter your age")
            }
            if (userData.gender == "") {
                canMove(false, "Please enter your gender")
            }
            if (userData.height == 0.0) {
                canMove(false, "Please enter your Height")
            }
            if (userData.weight == 0.0) {
                canMove(false, "Please enter your weight")
            }else {
                canMove(true, "")
            }
        } else {
            if (userData.stepsGoal == 0) {
                canMove(false, "Please enter your daily steps goal")
            }
            if (userData.caloriesIntake == 0) {
                canMove(false, "Please enter your calories intake")
            }
            if (userData.caloriesBurnedTarget == 0) {
                canMove(false, "Please enter your calories burned")
            }
            if (userData.waterIntake == 0) {
                canMove(false, "Please enter your daily water intake")
            }
            if (userData.sleepTime == 0) {
                canMove(false, "Please enter your sleep time")
            }else {
                canMove(true, "")
            }
        }

    }

}