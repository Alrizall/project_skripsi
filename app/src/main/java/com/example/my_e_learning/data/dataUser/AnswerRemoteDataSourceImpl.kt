package com.example.my_e_learning.data.dataUser

import android.net.Uri
import com.example.my_e_learning.data.UserAnswer
import com.example.my_e_learning.data.database.FirebaseAnswerQualifier
import com.example.my_e_learning.data.database.FirebaseMateriQualifier
import com.example.my_e_learning.data.database.FirebaseUserQualifier
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class AnswerRemoteDataSourceImpl @Inject constructor(
    @FirebaseAnswerQualifier private val databaseReference: DatabaseReference
) : AnswerRemoteDataSource {
    override suspend fun uploadAnswerData(data: UserAnswer): DataSourceVoid {
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