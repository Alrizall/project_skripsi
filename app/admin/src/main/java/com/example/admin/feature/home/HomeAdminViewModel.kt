package com.example.admin.feature.home

import androidx.lifecycle.ViewModel
import com.example.admin.R
import com.example.admin.local.PreferenceHelper
import com.example.admin.util.KeyConstant.USERNAME_KEY
import com.example.core.DashboardInformation
import com.example.core.DuedateInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeAdminViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper
    ) :
    ViewModel() {

    fun getUserName(): String {
        return preferenceHelper.getStringInSharedPreference(USERNAME_KEY)
    }
}
