package com.example.bewell.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bewell.model.UserProfile
import com.example.bewell.repositoryDef.UserProfileRepository
import com.example.bewell.viewstates.UserProfileState
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
                    _totalStepsEver.value = 0
                    _totalStepsEverDid.value = 0
                    userData.forEach {
                        _totalStepsEver.value += it.stepsGoal
                        _totalStepsEverDid.value += it.totalStepsDid
                    }
                    Log.d("sdfdjhdshsdfhjs", "userData.last(): ${userData.last()}")

                    _userProfileData.value = UserProfileState(userProfile = userData.last())
                } else {
                    _userProfileData.value = UserProfileState(error = "its error pai")
                }
            }
        }
    }

    fun userProfileViewModel() {
        viewModelScope.launch {
            _userProfileData.value.userProfile?.let {
                Log.d("checkingBeWellData", "userProfileData: ${it}")
                userProfileRepository.updateStepsGoal(monthId = it.id.toString(), stepsDid = it.totalStepsDid+1, calories =  calculateCalories(it.totalStepsDid+1))
            }?: run {
                Log.d("checkingBeWellData", "userProfileData: its null no data")
            }
        }
    }

    fun updateUserData() {
        viewModelScope.launch {
            _userProfileData.value.userProfile?.let {
                userProfileRepository.updateUserData(it)
            }
        }
    }

    fun createUserProfile() {
        viewModelScope.launch {
            _createUserProfileData.value.let { data->
                data.date = System.currentTimeMillis()
                userProfileRepository.createUserProfile(data)
            }
        }
    }

    fun insertNewDay(date: Long) {
        viewModelScope.launch {
            _userProfileData.value.userProfile?.let { data->
                userProfileRepository.createUserProfile(UserProfile(
                    date = date,
                    name = data.name,
                    age = data.age,
                    gender = data.gender,
                    height = data.height,
                    weight = data.weight,
                    stepsGoal = data.stepsGoal,
                    caloriesBurnedTarget = data.caloriesBurnedTarget,
                    caloriesIntake = data.caloriesIntake,
                    waterIntake = data.waterIntake,
                    sleepTime = data.sleepTime
                ))
            }
        }
    }

    private fun calculateCalories(steps: Int): Int {
        return (steps * 65 * 0.0005f).toInt()
    }

}