package com.example.bewell.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(var context: Context) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "bewell_preferences")

    companion object {
        val ON_BOARDING_DONE_KEY = booleanPreferencesKey("on_boarding_done")
        val USER_PROFILE_DONE_KEY = booleanPreferencesKey("user_profile_done")
    }

    suspend fun saveBooleanPref(key: Preferences.Key<Boolean>, value: Boolean) {
        context.dataStore.edit { pref->
            pref[key] = value
        }
    }

    fun getSavedBooleanPref(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return context.dataStore.data.map { prefs->
            prefs[key] ?: false
        }
    }

}