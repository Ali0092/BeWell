package com.example.bewell.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bewell.domain.model.UserProfile
import com.example.bewell.domain.repositoryDef.UserProfileRepository
import com.example.bewell.presentation.viewstates.UserProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel(private val userProfileRepository: UserProfileRepository) : ViewModel() {

    private val _userProfileData = MutableStateFlow(UserProfileState())
    val userProfileData: MutableStateFlow<UserProfileState> = _userProfileData

    private val _createUserProfileData = MutableStateFlow<UserProfile>(UserProfile())
    val createUserProfileData: StateFlow<UserProfile> = _createUserProfileData

    init {
        getUserProfileData()
    }

    private fun getUserProfileData() {
        viewModelScope.launch {
            _userProfileData.value = UserProfileState(isLoading = true)
            userProfileRepository.getUserProfile().collect { userProfile ->
                if (userProfile.isNotEmpty()) {
                    _userProfileData.value = UserProfileState(userProfile = userProfile.last())
                } else {
                    _userProfileData.value = UserProfileState(error = "its error pai")
                }
            }
        }
    }

    fun createUserProfile() {
        viewModelScope.launch {
            _createUserProfileData.value.let { data->
                userProfileRepository.createUserProfile(data)
            }
        }
    }

}