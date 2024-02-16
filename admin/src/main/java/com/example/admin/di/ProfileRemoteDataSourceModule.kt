package com.example.admin.di

import com.example.admin.data.dataAdmin.ProfileRemoteDataSource
import com.example.admin.data.dataAdmin.ProfileRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProfileRemoteDataSourceModule {
    @Binds
    fun bindsProfileRemoteDataSource(
        impl: ProfileRemoteDataSourceImpl
    ): ProfileRemoteDataSource
}