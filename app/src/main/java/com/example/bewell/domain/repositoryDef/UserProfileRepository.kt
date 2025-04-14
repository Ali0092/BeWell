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

    suspend fun updateStepsGoal(monthId: String , stepsDid: Int) {
        userProfileDao.updateStepsGoal(monthId,stepsDid)
    }

    fun getUserProfile(): Flow<List<UserProfile>> = userProfileDao.getUserProfile()

}