package com.example.bewell.presentation.viewstates

import com.example.bewell.domain.model.UserProfile

data class UserProfileState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile? = null,
    val error: String? = null
)