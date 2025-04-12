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

    private val _userProfileData = MutableStateFlow(UserProfileState())
    val userProfileData: MutableStateFlow<UserProfileState> = _userProfileData

    private val _totalStepsEver = MutableStateFlow<Long>(0)
    val totalStepsEver: MutableStateFlow<Long> = _totalStepsEver

    private val _totalStepsEverDid = MutableStateFlow<Long>(0)
    val totalStepsEverDid: MutableStateFlow<Long> = _totalStepsEverDid


    private val _createUserProfileData = MutableStateFlow<UserProfile>(UserProfile())
    val createUserProfileData: StateFlow<UserProfile> = _createUserProfileData

    init {
        getUserProfileData()
        _totalStepsEver.value = 0
        _totalStepsEverDid.value = 0
    }

    private fun getUserProfileData() {
        viewModelScope.launch {
            _userProfileData.value = UserProfileState(isLoading = true)
            userProfileRepository.getUserProfile().collect { userData ->
                if (userData.isNotEmpty()) {
                    userData.forEach {
                        _totalStepsEver.value += it.stepsGoal
                        _totalStepsEverDid.value += it.totalStepsDid
                    }
                    _userProfileData.value = UserProfileState(userProfile = userData.last())
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