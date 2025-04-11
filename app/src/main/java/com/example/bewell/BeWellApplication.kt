package com.example.bewell

import android.app.Application
import com.example.bewell.koin.appModule
import com.example.bewell.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BeWellApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BeWellApplication)
            modules(appModule, viewModelModule)
        }
    }

}