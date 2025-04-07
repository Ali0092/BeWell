package com.example.bewell.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StepsCounterViewModel: ViewModel() {

    private var _counter: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val counter: StateFlow<Int> = _counter

    private var _calories: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val calories: StateFlow<Int> = _calories

    init {
        _counter.value = 0
    }

    fun incrementCounter() {
        _counter.value += 1
        calculateCalories()
    }


    private fun calculateCalories() {
        _calories.value = (_counter.value * 65 * 0.0005f).toInt()
    }

}