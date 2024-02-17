package com.example.admin.di

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseStorageModule {

    private const val firebaseStorageUrl = ""

    @Provides
    fun provideFirebaseStorage(): StorageReference {
        return FirebaseStorage.getInstance().getReferenceFromUrl(firebaseStorageUrl)
    }

}