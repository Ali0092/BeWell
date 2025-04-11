package com.example.bewell.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bewell.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUserProfile(userProfile: UserProfile)

    @Update
    suspend fun updateUserProfileData(userProfile: UserProfile)

    @Query("SELECT * FROM user_profile")
    fun getUserProfile(): Flow<List<UserProfile>>

}