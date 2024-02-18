package com.example.admin.feature.tugas

import androidx.lifecycle.ViewModel
import com.example.admin.data.TugasInformation
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.example.admin.data.database.MateriRemoteDataSource
import com.example.admin.data.database.TugasRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TugasViewModel @Inject constructor(private val tugasRemoteDataSource: TugasRemoteDataSource) :
    ViewModel() {

    suspend fun uploadTugasData(
        data: TugasInformation,
    ): DataSourceVoid = tugasRemoteDataSource.uploadTugasData(data)
}