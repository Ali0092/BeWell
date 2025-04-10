package com.example.bewell.koin

import com.example.bewell.data.datastore.DataStoreManager
import com.example.bewell.presentation.viewmodel.StepsCounterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    //datastore manager
    single { DataStoreManager(androidContext()) }
}

val viewModelModule = module {
    single { StepsCounterViewModel() }
}