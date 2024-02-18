package com.example.my_e_learning.data.dataUser

import com.example.my_e_learning.data.MateriInformation
import kotlinx.coroutines.flow.Flow

interface MateriRemoteDataSource {

    fun getMateriData(): Flow<DataSourceHelper<List<MateriInformation>>>

}