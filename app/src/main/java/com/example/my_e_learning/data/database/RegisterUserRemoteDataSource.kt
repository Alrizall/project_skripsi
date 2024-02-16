package com.example.my_e_learning.data.database

import com.example.my_e_learning.data.UserInformation
import com.example.my_e_learning.data.dataUser.DataSourceHelper
import com.example.my_e_learning.data.dataUser.DataSourceVoid
import kotlinx.coroutines.flow.Flow

interface RegisterUserRemoteDataSource {

    fun getFirebaseUserData(): Flow<DataSourceHelper<List<UserInformation>>>

    suspend fun uploadFirebaseUserData(data: UserInformation): DataSourceVoid
}