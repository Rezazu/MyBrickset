package com.example.mybrickset.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AuthPreferences(
    @ApplicationContext private val context: Context,
) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "hash")


    private val USER_HASH = stringPreferencesKey("hash")

    fun getUserHash(): Flow<String> {
        val preferences = context.dataStore.data.map { preferences ->
            preferences[USER_HASH] ?: ""
        }
        return preferences
    }

    suspend fun saveUserHash(userHash: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_HASH] = userHash
        }
    }

    suspend fun deleteUserHash() {
        context.dataStore.edit { preferences ->
            preferences[USER_HASH] = ""
        }
    }
}