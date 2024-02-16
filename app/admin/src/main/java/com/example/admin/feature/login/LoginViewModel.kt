package com.example.admin.feature.login

import androidx.lifecycle.ViewModel
import com.example.admin.local.PreferenceHelper
import com.example.admin.util.KeyConstant.ADMIN_KEY
import com.example.admin.util.KeyConstant.USERNAME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
) :
    ViewModel() {

    fun saveUserName(username: String) {
        preferenceHelper.saveStringInSharedPreference(USERNAME_KEY, username)
    }
}