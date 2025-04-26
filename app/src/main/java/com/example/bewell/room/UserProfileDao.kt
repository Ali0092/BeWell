package com.example.bewell.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bewell.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUserProfile(userProfile: UserProfile)

    @Update
    suspend fun updateUserData(userProfile: UserProfile)

    @Query("Update user_profile SET totalStepsDid = :stepsDid, totalCaloriesBurned = :calories WHERE id = :monthId")
    suspend fun updateStepsGoal(monthId: String, stepsDid: Int, calories: Int)

    @Query("SELECT * FROM user_profile")
    fun getUserProfile(): Flow<List<UserProfile>>

}