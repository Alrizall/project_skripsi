package com.example.admin.data.database

import com.example.admin.data.UserInformation
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import kotlinx.coroutines.flow.Flow

interface RegisterUserRemoteDataSource {

    fun getFirebaseUserData(): Flow<DataSourceHelper<List<UserInformation>>>

    suspend fun uploadFirebaseUserData(data: UserInformation): DataSourceVoid
}