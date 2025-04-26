package com.example.bewell.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bewell.model.UserProfile

@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
abstract class BeWellDataBase : RoomDatabase() {

    abstract fun userProfileDao() : UserProfileDao

}