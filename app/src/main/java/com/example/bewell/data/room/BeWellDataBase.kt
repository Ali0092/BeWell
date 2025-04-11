package com.example.bewell.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bewell.domain.model.DailyGoal
import com.example.bewell.domain.model.UserProfile

@Database(entities = [UserProfile::class, DailyGoal::class], version = 1, exportSchema = false)
abstract class BeWellDataBase : RoomDatabase() {

    abstract fun userProfileDao() : UserProfileDao

}