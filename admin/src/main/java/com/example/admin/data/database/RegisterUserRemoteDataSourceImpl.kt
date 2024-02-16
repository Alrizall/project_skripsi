package com.example.admin.data.database

import com.example.admin.data.UserInformation
import com.example.admin.data.dataAdmin.DataSourceHelper
import com.example.admin.data.dataAdmin.DataSourceVoid
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegisterUserRemoteDataSourceImpl @Inject constructor(@FirebaseUserQualifier private val databasePlaceReference: DatabaseReference) :
    RegisterUserRemoteDataSource {
    override fun getFirebaseUserData(): Flow<DataSourceHelper<List<UserInformation>>> {
        return databasePlaceReference.valueEventFlow().map { value ->
            when (value) {
                is PushFirebase.Changed -> {
                    val result = value.snapshot.children.mapNotNull {
                        it.getValue(UserInformation::class.java)
                    }.toList()
                    DataSourceHelper.Success(result)
                }

                is PushFirebase.Cancelled -> {
                    DataSourceHelper.Error("${value.error.toException()}")
                }
            }
        }.catch { DataSourceHelper.Error("${it.message}") }

    }

    override suspend fun uploadFirebaseUserData(data: UserInformation): DataSourceVoid {
        val result: CompletableDeferred<DataSourceVoid> = CompletableDeferred()
        try {
            databasePlaceReference.push().setValue(data).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.complete(DataSourceVoid.Success)
                } else {
                    // If sign in fails, display a message to the user.
                    result.complete(DataSourceVoid.Error("Failure : ${task.exception}"))
                }
            }
        } catch (e: Exception) {
            result.complete(DataSourceVoid.Error("Failure : ${e.message}"))
        }
        return result.await()
    }
}