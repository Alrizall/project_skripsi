package com.example.my_e_learning.fitur.materi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.data.dataUser.DataSourceHelper
import com.example.my_e_learning.data.dataUser.MateriRemoteDataSource
import com.example.my_e_learning.data.model.MateriUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MateriViewModel @Inject constructor(private val materiRemoteDataSource: MateriRemoteDataSource) :
    ViewModel() {
    private val _materiDataState: MutableStateFlow<MateriUiState> = MutableStateFlow(
        MateriUiState.initial()
    )
    val materiDataSate: StateFlow<MateriUiState> get() = _materiDataState.asStateFlow()

    init {
        viewModelScope.launch {
            materiRemoteDataSource.getMateriData().onEach { result ->
                when (result) {
                    is DataSourceHelper.Error -> _materiDataState.update { currentUiState ->
                        currentUiState.copy(
                            errorMessage = result.errorMessage,
                            materi = emptyList()
                        )
                    }

                    is DataSourceHelper.Success -> _materiDataState.update { currentUiState ->
                        currentUiState.copy(errorMessage = "", materi = result.data)

                    }
                }
            }.launchIn(this)
        }
    }

}