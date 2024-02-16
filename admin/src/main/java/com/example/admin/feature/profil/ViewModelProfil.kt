package com.example.admin.feature.profil

import androidx.lifecycle.ViewModel
import com.example.admin.local.PreferenceHelper
import com.example.admin.util.KeyConstant
import com.example.admin.util.KeyConstant.ADMIN_KEY
import com.example.admin.util.KeyConstant.USERNAME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@HiltViewModel
class ViewModelProfil  @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
) :
    ViewModel() {

    fun getUserName(): String {
        return preferenceHelper.getStringInSharedPreference(KeyConstant.USERNAME_KEY)
    }

    fun deleteUserName() {
        preferenceHelper.deleteSharedPreference(USERNAME_KEY)
        preferenceHelper.deleteSharedPreference(ADMIN_KEY)
    }
}