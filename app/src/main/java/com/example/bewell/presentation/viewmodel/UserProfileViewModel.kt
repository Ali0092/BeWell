package com.example.bewell.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bewell.domain.model.UserProfile
import com.example.bewell.domain.repositoryDef.UserProfileRepository
import com.example.bewell.presentation.viewstates.UserProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel(private val userProfileRepository: UserProfileRepository) : ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfileState())
    val userProfile: MutableStateFlow<UserProfileState> = _userProfile

    private val _userData = MutableStateFlow<UserProfile?>(null)
    val userData: StateFlow<UserProfile?> = _userData

    init {
        getUserProfileData()
        _userData.value = null
    }

    private fun getUserProfileData() {
        viewModelScope.launch {
            _userProfile.value = UserProfileState(isLoading = true)
            userProfileRepository.getUserProfile().collect { userProfile ->
                if (userProfile.isEmpty()) {
                    _userProfile.value = UserProfileState(userProfile = userProfile.first())
                } else {
                    _userProfile.value = UserProfileState(error = "its error pai")
                }
            }
        }
    }

    fun createUserProfile() {
        viewModelScope.launch {
            _userData.value?.let { data->
                userProfileRepository.createUserProfile(data)
            }
        }
    }

}