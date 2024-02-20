package com.example.my_e_learning.fitur.tugas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_e_learning.R
import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.data.UserAnswer
import com.example.my_e_learning.data.dataUser.AnswerRemoteDataSourceImpl
import com.example.my_e_learning.data.dataUser.DataSourceHelper
import com.example.my_e_learning.data.dataUser.DataSourceVoid
import com.example.my_e_learning.data.dataUser.MateriRemoteDataSource
import com.example.my_e_learning.data.dataUser.TugasRemoteDataSource
import com.example.my_e_learning.data.model.MateriUiState
import com.example.my_e_learning.data.model.TugasUiState
import com.example.my_e_learning.local.PreferenceHelper
import com.example.my_e_learning.util.KeyConstant
import com.example.my_e_learning.util.KeyConstant.ANSWER_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class TugasViewModel @Inject constructor(
    private val tugasRemoteDataSource: TugasRemoteDataSource,
    private val preferenceHelper: PreferenceHelper,
    private val answerRemoteDataSourceImpl: AnswerRemoteDataSourceImpl
) :
    ViewModel() {
    private val _tugasDataState: MutableStateFlow<TugasUiState> = MutableStateFlow(
        TugasUiState.initial()
    )
    val tugasDataState: StateFlow<TugasUiState> get() = _tugasDataState.asStateFlow()

    suspend fun uploadAnswerFirebase(data: UserAnswer): DataSourceVoid =
       answerRemoteDataSourceImpl.uploadAnswerData(data)

    init {
        viewModelScope.launch {
            tugasRemoteDataSource.getTugasData().onEach { result ->
                when (result) {
                    is DataSourceHelper.Error -> _tugasDataState.update { currentUiState ->
                        currentUiState.copy(errorMessage = result.errorMessage, tugas = emptyList())
                    }

                    is DataSourceHelper.Success -> _tugasDataState.update { currentUiState ->
                        currentUiState.copy(errorMessage = "", tugas = result.data)

                    }
                }
            }.launchIn(this)
        }
    }

    //fun lock jawaban untuk save pertanyaan yang akan dijawab
    fun saveLockQuestion(data: TugasInformation, uid: String) {
        val listOfHasil = preferenceHelper.getStringInSharedPreference(uid)
        if (listOfHasil.isNotEmpty()) {
            val consumeType: Type =
                object : TypeToken<List<TugasInformation>>() {}.type
            val consume: MutableList<TugasInformation> =
                Gson().fromJson<List<TugasInformation>?>(listOfHasil, consumeType).toMutableList()
            consume.add(data)
            val saveValue = Gson().toJson(consume)
            preferenceHelper.saveStringInSharedPreference(uid, saveValue)
        } else {
            val consume: List<TugasInformation> = listOf(data)
            val saveValue = Gson().toJson(consume)
            preferenceHelper.saveStringInSharedPreference(uid, saveValue)
        }

    }

    //fun untuk jawaban sudah di lock
    fun isAnswerLock(data: TugasInformation, uid: String): Boolean {
        val listOfHasil = preferenceHelper.getStringInSharedPreference(uid)
        if (listOfHasil.isNotEmpty()) {
            val consumeType: Type =
                object : TypeToken<List<TugasInformation>>() {}.type
            val consume: MutableList<TugasInformation> =
                Gson().fromJson<List<TugasInformation>?>(listOfHasil, consumeType).toMutableList()
            val returnValue = consume.any { it.description == data.description }
            return returnValue
        } else return false

    }
}