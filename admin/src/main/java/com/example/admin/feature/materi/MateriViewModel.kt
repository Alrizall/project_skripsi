package com.example.admin.feature.materi

import androidx.lifecycle.ViewModel
import com.example.admin.data.MateriInformation
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.example.admin.data.database.MateriRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MateriViewModel @Inject constructor(private val materiRemoteDataSource: MateriRemoteDataSource) :
    ViewModel() {

    suspend fun uploadMateriData(
        data: MateriInformation,
    ): DataSourceVoid = materiRemoteDataSource.uploadMateriData(data)
}