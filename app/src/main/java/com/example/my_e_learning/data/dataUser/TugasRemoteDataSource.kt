package com.example.my_e_learning.data.dataUser

import com.example.my_e_learning.data.TugasInformation
import kotlinx.coroutines.flow.Flow

interface TugasRemoteDataSource {

    fun getTugasData(): Flow<DataSourceHelper<List<TugasInformation>>>
}