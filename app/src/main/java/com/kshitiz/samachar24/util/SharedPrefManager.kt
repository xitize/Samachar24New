package com.kshitiz.samachar24.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "Samachar24Pref"
        private const val KEY_LAST_UPDATE_TIMESTAMP = "last_update_timestamp"
    }

    fun saveString(key: String, value: String) {
        sharedPreferences.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun saveLong(key: String, value: Long) = sharedPreferences.edit { putLong(key, value) }

    fun getLong(key: String, defaultValue: Long = 0L): Long =
        sharedPreferences.getLong(key, defaultValue)

    fun clear() = sharedPreferences.edit { clear() }
}