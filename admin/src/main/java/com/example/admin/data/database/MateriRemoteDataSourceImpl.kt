package com.example.admin.data.database

import android.net.Uri
import com.example.admin.data.MateriInformation
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MateriRemoteDataSourceImpl @Inject constructor(
    private val storageReference: StorageReference,
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

    override suspend fun uploadMateriData(data: MateriInformation): DataSourceVoid {
        val result: CompletableDeferred<DataSourceVoid> = CompletableDeferred()
        if (data.image != null) {
            val imageUri = Uri.parse(data.image)
            val segment = imageUri.lastPathSegment
            if (segment != null) {
                val reference = storageReference.child(segment)
                with(reference.putFile(imageUri)) {
                    addOnSuccessListener {
                        with(reference.downloadUrl) {
                            addOnSuccessListener {
                                data.image = it.toString()
                                databaseReference.push().setValue(data)
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            result.complete(DataSourceVoid.Success)
                                        } else {
                                            result.complete(DataSourceVoid.Error("Failure : ${task.exception?.message}"))
                                        }
                                    }
                            }

                            addOnFailureListener {
                                result.complete(DataSourceVoid.Error("Failure : ${it.message}"))
                            }
                        }
                    }
                    addOnFailureListener {
                        result.complete(DataSourceVoid.Error("Failure : ${it.message}"))
                    }
                }
            }
        } else {
            databaseReference.push().setValue(data).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.complete(DataSourceVoid.Success)
                } else {
                    // If sign in fails, display a message to the user.
                    result.complete(DataSourceVoid.Error("Failure : ${task.exception}"))
                }
            }
        }
        return result.await()
    }


}