package com.example.my_e_learning.fitur.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_e_learning.data.UserInformation
import com.example.my_e_learning.data.dataUser.AuthenticatedUserInfo
import com.example.my_e_learning.data.dataUser.DataSourceHelper
import com.example.my_e_learning.data.dataUser.DataSourceVoid
import com.example.my_e_learning.data.dataUser.ProfileRemoteDataSource
import com.example.my_e_learning.data.database.RegisterUserRemoteDataSource
import com.example.my_e_learning.data.model.ProfileUiState
import com.example.my_e_learning.data.model.UserProfileUiState
import com.example.my_e_learning.local.PreferenceHelper
import com.example.my_e_learning.util.KeyConstant.ADMIN_KEY
import com.example.my_e_learning.util.KeyConstant.MATERI_KEY
import com.example.my_e_learning.util.KeyConstant.USERNAME_KEY
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
class LoginViewModel @Inject constructor(
    private val loginRemoteDataSource: ProfileRemoteDataSource,
    private val getRemoteDataSource: RegisterUserRemoteDataSource,
) : ViewModel() {
    suspend fun signIn(email: String, password: String): DataSourceVoid =
        loginRemoteDataSource.signIn(email, password)

    private val _userDataState: MutableStateFlow<UserProfileUiState> = MutableStateFlow(
        UserProfileUiState.initial()
    )
    val userDataState: StateFlow<UserProfileUiState> get() = _userDataState.asStateFlow()

    private val _profilState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState.initial()
    )
    val profilState: StateFlow<ProfileUiState> get() = _profilState.asStateFlow()

    init {
        viewModelScope.launch {
            loginRemoteDataSource.getUserProfile().onEach { result ->
                when (result) {
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

    fun initLogout(onComplete: () -> Unit) =
        loginRemoteDataSource.initLogout(onComplete)

    fun getDetailUser(uid: String) {
        viewModelScope.launch {
            getRemoteDataSource.getFirebaseUserData().onEach { result ->
                when (result) {
                    is DataSourceHelper.Error -> _profilState.update { currentUiState ->
                        currentUiState.copy(errorMessage = result.errorMessage, user = null)
                    }

                    is DataSourceHelper.Success -> {
                        _profilState.update { currentUiState ->
                            currentUiState.copy(
                                errorMessage = "",
                                user = result.data.filter { it.id!! == uid }
                                    .map { UserInformation(it.id,it.username, it.password, it.alamat, it.no_telepon) }.first())
                        }
                    }
                }
            }.launchIn(this)
        }
    }

}
