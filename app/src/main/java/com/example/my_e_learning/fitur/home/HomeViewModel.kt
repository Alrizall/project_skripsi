package com.example.my_e_learning.fitur.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_e_learning.R
import com.example.my_e_learning.data.DashboardInformation
import com.example.my_e_learning.data.DuedateInformation
import com.example.my_e_learning.data.dataUser.DataSourceHelper
import com.example.my_e_learning.data.dataUser.ProfileRemoteDataSource
import com.example.my_e_learning.data.database.RegisterUserRemoteDataSource
import com.example.my_e_learning.data.model.UserProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private  val loginRemoteDataSource : ProfileRemoteDataSource) :
    ViewModel() {

    private val _userDataState: MutableStateFlow<UserProfileUiState> = MutableStateFlow(
        UserProfileUiState.initial())
    val userDataState : StateFlow<UserProfileUiState> get() = _userDataState.asStateFlow()

    init {
        viewModelScope.launch {
            loginRemoteDataSource.getUserProfile().onEach{ result ->
                when(result) {
                    is DataSourceHelper.Error -> _userDataState.update { currentUiState ->
                        currentUiState.copy(errorMessage = result.errorMessage, user = null)
                    }
                    is DataSourceHelper.Success -> _userDataState.update { currentUiState ->
                        currentUiState.copy(errorMessage = "", user = result.data)

                    }
                }
            }.launchIn(this)
        }
    }
    fun dashboardInformationProvider(): List<DashboardInformation> {

        return listOf(
            DashboardInformation(1, R.drawable.img_kurikulum, "Kurikulum Baru"),
            DashboardInformation(2, R.drawable.img_bakti_kominfo, " Kemajuan Pendidikan "),
            DashboardInformation(3, R.drawable.img_user_interface, " Kemajuan Desain UI "),
            DashboardInformation(4, R.drawable.img_anak_sd, " Infrastruktur Sekolah Di Pedesaan  ")
        )
    }

    fun duedateInformationProvider(): List<DuedateInformation> {

        return listOf(
            DuedateInformation(1, "Tugas 1, dateline 1 hari lagi"),
            DuedateInformation(2,  " Tugas 2, Dateline 2 hari lagi"),
            DuedateInformation(3,  " Tugas 3, Dateline 3 hari lagi"),
        )
    }

}