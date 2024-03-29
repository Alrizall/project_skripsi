package com.example.my_e_learning.local

/**
 * Created by Ian Damping on 30,June,2020
 * Github https://github.com/iandamping
 * Indonesia.
 */
interface PreferenceHelper {

    fun saveStringInSharedPreference(key: String, value: String?)

    fun getStringInSharedPreference(key: String): String

    fun deleteSharedPreference(key: String)

    fun deleteAllSharedPrefrence()
}
