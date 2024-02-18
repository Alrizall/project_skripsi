package com.example.admin.data.database

import android.net.Uri
import com.example.admin.data.TugasInformation
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TugasRemoteDataSourceImpl@Inject constructor(
    private val storageReference: StorageReference,
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

    override suspend fun uploadTugasData (data: TugasInformation): DataSourceVoid {
        val result: CompletableDeferred<DataSourceVoid> = CompletableDeferred()
        databaseReference.push().setValue(data).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                result.complete(DataSourceVoid.Success)
            } else {
                // If sign in fails, display a message to the user.
                result.complete(DataSourceVoid.Error("Failure : ${task.exception}"))
            }
        }
        return result.await()
    }
}