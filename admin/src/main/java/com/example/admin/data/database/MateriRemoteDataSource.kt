package com.example.admin.data.database

import android.net.Uri
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.example.admin.data.MateriInformation
import kotlinx.coroutines.flow.Flow

interface MateriRemoteDataSource {

    fun getMateriData(): Flow<DataSourceHelper<List<MateriInformation>>>

    suspend fun uploadMateriData(
        data: MateriInformation,
    ): DataSourceVoid
}