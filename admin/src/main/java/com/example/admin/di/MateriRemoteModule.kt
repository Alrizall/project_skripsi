package com.example.admin.di

import com.example.admin.data.database.FirebaseMateriQualifier
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MateriRemoteModule {

    private const val MATERI_NODE = "materi node"

    @Provides
    @FirebaseMateriQualifier
    fun provideFirebaseDatabase(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.child(MATERI_NODE)
    }
}