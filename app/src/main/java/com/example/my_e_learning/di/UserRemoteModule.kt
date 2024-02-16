package com.example.my_e_learning.di

import com.example.my_e_learning.data.database.FirebaseUserQualifier
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserRemoteModule {

    private const val USER_NODE = "user node"

    @Provides
    @FirebaseUserQualifier
    fun provideFirebaseDatabase(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.child(USER_NODE)
    }

}