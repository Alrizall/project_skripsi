package com.example.admin.di

import com.example.admin.data.database.FirebaseMateriQualifier
import com.example.admin.data.database.FirebaseTugasQualifier
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TugasRemoteModule {

    private const val TUGAS_NODE = "tugas node"

    @Provides
    @FirebaseTugasQualifier
    fun provideFirebaseDatabase(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.child(TUGAS_NODE)
    }
}