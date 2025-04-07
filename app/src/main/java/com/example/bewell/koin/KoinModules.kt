package com.example.bewell.koin

import com.example.bewell.ui.viewmodel.StepsCounterViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { StepsCounterViewModel() }
}