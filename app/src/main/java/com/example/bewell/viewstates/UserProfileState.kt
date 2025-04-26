package com.example.bewell.viewstates

import com.example.bewell.model.UserProfile

data class UserProfileState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile? = null,
    val error: String? = null
)