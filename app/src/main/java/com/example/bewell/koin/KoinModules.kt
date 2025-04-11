package com.example.bewell.koin

import androidx.room.Room
import com.example.bewell.data.datastore.DataStoreManager
import com.example.bewell.data.room.BeWellDataBase
import com.example.bewell.data.room.UserProfileDao
import com.example.bewell.domain.repositoryDef.UserProfileRepository
import com.example.bewell.presentation.viewmodel.StepsCounterViewModel
import com.example.bewell.presentation.viewmodel.UserProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.math.sin

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
    single { StepsCounterViewModel() }
    single { UserProfileViewModel(get()) }
}