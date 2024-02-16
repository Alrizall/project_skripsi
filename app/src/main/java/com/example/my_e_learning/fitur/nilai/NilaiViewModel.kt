package com.example.my_e_learning.fitur.nilai

import androidx.lifecycle.ViewModel
import com.example.my_e_learning.R
import com.example.my_e_learning.data.DashboardInformation
import com.example.my_e_learning.data.NilaiInformation
import com.example.my_e_learning.local.PreferenceHelper
import com.example.my_e_learning.util.KeyConstant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Type
import javax.inject.Inject
@HiltViewModel
class NilaiViewModel @Inject constructor(private val preferenceHelper: PreferenceHelper) : ViewModel() {
    fun nilaiInformationProvider(): List<NilaiInformation> {
        val consumeType: Type =
            object : TypeToken<List<NilaiInformation>>() {}.type
        val userName = preferenceHelper.getStringInSharedPreference(KeyConstant.USERNAME_KEY)
        val listOfHasil = preferenceHelper.getStringInSharedPreference("$userName${userName.first()}")
        return if (!listOfHasil.isNullOrEmpty()) {
                Gson().fromJson<List<NilaiInformation>?>(listOfHasil, consumeType)

        }else {
//            listOf (
//                NilaiInformation(1, "hasil nilai =",90),
//                NilaiInformation(2,"hasil nilai =", 80)
//            )
            emptyList()
        }
    }
}