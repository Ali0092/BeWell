package com.example.bewell.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashHandlingViewModel: ViewModel() {

    private val _isSplashLoading = MutableStateFlow(true)
    val isSplashLoading: StateFlow<Boolean> = _isSplashLoading

    init {
        _isSplashLoading.value = false
    }

    fun setSplashLoadingStatus(status: Boolean) {
        _isSplashLoading.value = status
    }

}