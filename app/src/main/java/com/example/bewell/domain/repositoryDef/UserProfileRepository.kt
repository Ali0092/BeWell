package com.example.bewell.domain.repositoryDef

import com.example.bewell.data.room.UserProfileDao
import com.example.bewell.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

class UserProfileRepository(private val userProfileDao: UserProfileDao) {

    suspend fun createUserProfile(userProfile: UserProfile) {
        userProfileDao.createUserProfile(userProfile)
    }

    suspend fun updateStepsGoal(monthId: String, stepsDid: Int, calories: Int) {
        userProfileDao.updateStepsGoal(monthId, stepsDid, calories)
    }

    suspend fun updateUserData(userData: UserProfile) {
        userProfileDao.updateUserData(userData)
    }

    fun getUserProfile(): Flow<List<UserProfile>> = userProfileDao.getUserProfile()

}