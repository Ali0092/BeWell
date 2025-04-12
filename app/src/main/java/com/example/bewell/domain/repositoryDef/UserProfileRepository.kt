package com.example.bewell.domain.repositoryDef

import android.util.Log
import com.example.bewell.data.room.UserProfileDao
import com.example.bewell.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

class UserProfileRepository(private val userProfileDao: UserProfileDao) {

    suspend fun createUserProfile(userProfile: UserProfile) {
        Log.d("checkingouttheUserProfile", "createUserProfile: ${userProfile} ")
        userProfileDao.createUserProfile(userProfile)
    }

    suspend fun updateUserProfileData(userProfile: UserProfile) {
        userProfileDao.updateUserProfileData(userProfile)
    }

    fun getUserProfile(): Flow<List<UserProfile>> = userProfileDao.getUserProfile()

}