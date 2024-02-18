package com.example.my_e_learning.data.dataUser

import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.data.database.FirebaseTugasQualifier
import com.example.my_e_learning.data.database.PushFirebase
import com.example.my_e_learning.data.database.valueEventFlow
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TugasRemoteDataSourceImpl@Inject constructor(
    @FirebaseTugasQualifier private val databaseReference: DatabaseReference
) : TugasRemoteDataSource  {

    override fun getTugasData(): Flow<DataSourceHelper<List<TugasInformation>>> {
        return databaseReference.valueEventFlow().map { value ->
            when (value) {
                is PushFirebase.Changed -> {
                    val result = value.snapshot.children.mapNotNull {
                        it.getValue(TugasInformation ::class.java)
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