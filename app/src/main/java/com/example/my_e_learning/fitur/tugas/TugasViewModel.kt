package com.example.my_e_learning.fitur.tugas

import androidx.lifecycle.ViewModel
import com.example.my_e_learning.R
import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.local.PreferenceHelper
import com.example.my_e_learning.util.KeyConstant
import com.example.my_e_learning.util.KeyConstant.ANSWER_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class TugasViewModel @Inject constructor(private val preferenceHelper: PreferenceHelper) : ViewModel(){
    fun tugasInformationProvider(id: Int): TugasInformation {

        return when(id){
            1 -> {
                TugasInformation(1,"Jendela antar muka yang menghubungkan program dengan pengguna disebut?",null)
            }
            // Desain Interface
            2 -> {
                TugasInformation(2,"Sebutkan contoh tentang interface!",null)
            }
            else -> {
                TugasInformation(3,"Sebutkan 3 desain tools yang biasa digunakan untuk UI", null)
            }
        }
    }
    fun lockJawaban (data: TugasInformation){
        val userName = preferenceHelper.getStringInSharedPreference(KeyConstant.USERNAME_KEY)
        val listOfHasil = preferenceHelper.getStringInSharedPreference(userName)
        if (!listOfHasil.isNullOrEmpty()){
            val consumeType: Type =
                object : TypeToken<List<TugasInformation>>() {}.type
            val consume: MutableList<TugasInformation> =
                Gson().fromJson<List<TugasInformation>?>(listOfHasil, consumeType).toMutableList()
            consume.add(data)
            val saveValue = Gson().toJson(consume)
            preferenceHelper.saveStringInSharedPreference(userName,saveValue)
        }else {
            val consumeType: Type =
                object : TypeToken<List<TugasInformation>>() {}.type
            val consume: List<TugasInformation> = listOf(data)
            val saveValue = Gson().toJson(consume)
            preferenceHelper.saveStringInSharedPreference(userName,saveValue)
        }

    }
    fun locked (data: TugasInformation) : Boolean {
        val userName = preferenceHelper.getStringInSharedPreference(KeyConstant.USERNAME_KEY)
        val listOfHasil = preferenceHelper.getStringInSharedPreference(userName)
        if (!listOfHasil.isNullOrEmpty()){
            val consumeType: Type =
                object : TypeToken<List<TugasInformation>>() {}.type
            val consume: MutableList<TugasInformation> =
                Gson().fromJson<List<TugasInformation>?>(listOfHasil, consumeType).toMutableList()
            val returnValue = consume.any{it.id == data.id}
            return returnValue
        }else return false

    }
}