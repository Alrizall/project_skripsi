package com.example.admin.data.database

import com.example.admin.data.TugasInformation
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import kotlinx.coroutines.flow.Flow

interface TugasRemoteDataSource {

    fun getTugasData(): Flow<DataSourceHelper<List<TugasInformation>>>

    suspend fun uploadTugasData(
        data: TugasInformation,
    ): DataSourceVoid
}