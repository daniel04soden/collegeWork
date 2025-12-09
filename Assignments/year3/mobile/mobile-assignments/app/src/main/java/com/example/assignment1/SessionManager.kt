package com.example.assignment1

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SessionManager(context: Context) {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "session_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveUserSession(userId: Long) {
        sharedPreferences.edit { putLong("LOGGED_IN_USER_ID", userId) }
    }

    fun getLoggedInUserId(): Long {
        return sharedPreferences.getLong("LOGGED_IN_USER_ID", -1L)
    }

    fun clearSession() {
        sharedPreferences.edit { remove("LOGGED_IN_USER_ID") }
    }
}
