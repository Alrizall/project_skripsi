package com.example.my_e_learning.data.dataUser

import android.net.Uri
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.data.database.FirebaseMateriQualifier
import com.example.my_e_learning.data.database.PushFirebase
import com.example.my_e_learning.data.database.valueEventFlow
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MateriRemoteDataSourceImpl @Inject constructor(
    @FirebaseMateriQualifier private val databaseReference: DatabaseReference
) : MateriRemoteDataSource {


    override fun getMateriData(): Flow<DataSourceHelper<List<MateriInformation>>> {
        return databaseReference.valueEventFlow().map { value ->
            when (value) {
                is PushFirebase.Changed -> {
                    val result = value.snapshot.children.mapNotNull {
                        it.getValue(MateriInformation::class.java)
                    }.toList()
                    DataSourceHelper.Success(result)
                }

                is PushFirebase.Cancelled -> {
                    DataSourceHelper.Error("${value.error.toException()}")
                }
            }
        }.catch { DataSourceHelper.Error("${it.message}") }
    }

}