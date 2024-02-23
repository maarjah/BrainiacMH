package com.example.brainiac

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStoreManager(private val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Goals")
        val countOneData = intPreferencesKey("COUNT_ONE")
        val countTwoData = intPreferencesKey("COUNT_TWO")
        val countYesData = intPreferencesKey("COUNT_YES")
        val countConfirmData = intPreferencesKey("COUNT_COUNT")
    }


    suspend fun setCountOneData(liveData: Int) {
        context.dataStore.edit { pref ->
            pref[countOneData] = liveData
        }

    }

    suspend fun setCountTwoData(liveData: Int) {
        context.dataStore.edit { pref ->
            pref[countTwoData] = liveData
        }
    }


    suspend fun saveConfirmData(countYes: Int, countConfirm: Int) {
        context.dataStore.edit { pref ->
            pref[countYesData] = countYes
            pref[countConfirmData] = countConfirm
        }
    }


    val getCountOneData: Flow<Int> = context.dataStore.data
        .map { prefOne ->
            prefOne[countOneData] ?: 0

        }


    val getCountTwoData: Flow<Int> = context.dataStore.data
        .map { prefTwo ->
            prefTwo[countTwoData] ?: 0

        }

    val getCountYesData: Flow<Int> = context.dataStore.data
        .map { prefYes ->
            prefYes[countYesData] ?: 0
        }

    val getCountConfirmData: Flow<Int> = context.dataStore.data
        .map { prefConfirm ->
            prefConfirm[countConfirmData] ?: 0
        }


    val countOneDataFlow: Flow<Int> = context.dataStore.data.map { prefOne ->
        val storedOne = prefOne[countOneData] ?: 0
        storedOne
    }

    val countTwoDataFlow: Flow<Int> = context.dataStore.data.map { prefOne ->
        val storedOne = prefOne[countTwoData] ?: 0
        storedOne
    }
}

