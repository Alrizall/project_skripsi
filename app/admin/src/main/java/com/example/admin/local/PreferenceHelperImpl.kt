package com.example.admin.local

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor
) : PreferenceHelper {

    override fun saveStringInSharedPreference(key: String, value: String?) {
        editor.putString(key, value).apply()
    }

    override fun getStringInSharedPreference(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun deleteSharedPreference(key: String) {
        editor.remove(key).apply()
    }

    override fun deleteAllSharedPrefrence() {
        editor.clear().apply()
    }
}
