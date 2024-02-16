package com.example.admin

import androidx.lifecycle.ViewModel
import com.example.admin.data.UserInformation
import com.example.admin.data.dataAdmin.AuthenticatedUserInfo
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.example.admin.data.dataAdmin.ProfileRemoteDataSource
import com.example.admin.data.database.RegisterUserRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelDataUser @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource,
    private val registerUserRemoteDataSource: RegisterUserRemoteDataSource
) : ViewModel() {
    suspend fun signUp(email: String, password: String): DataSourceHelper<AuthenticatedUserInfo> =
        profileRemoteDataSource.signUp(email, password)
    suspend fun uploadFirebaseUserData(data: UserInformation): DataSourceVoid =
        registerUserRemoteDataSource.uploadFirebaseUserData(data)
}
