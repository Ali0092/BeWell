package com.example.bewell.koin

import androidx.room.Room
import com.example.bewell.datastore.DataStoreManager
import com.example.bewell.room.BeWellDataBase
import com.example.bewell.room.UserProfileDao
import com.example.bewell.repositoryDef.UserProfileRepository
import com.example.bewell.viewmodel.SplashHandlingViewModel
import com.example.bewell.viewmodel.UserProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    //datastore manager
    single { DataStoreManager(androidContext()) }
    //room
    single<BeWellDataBase> {
        Room.databaseBuilder(androidContext(), BeWellDataBase::class.java, "bewell_db").build()
    }
    //daos
    factory<UserProfileDao> { get<BeWellDataBase>().userProfileDao() }
    //repository
    factory<UserProfileRepository> { UserProfileRepository(get()) }
}

val viewModelModule = module {
    single { SplashHandlingViewModel() }
    single { UserProfileViewModel(get()) }
}