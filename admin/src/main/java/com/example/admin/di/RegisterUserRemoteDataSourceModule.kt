package com.example.admin.di

import com.example.admin.data.database.RegisterUserRemoteDataSource
import com.example.admin.data.database.RegisterUserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RegisterUserRemoteDataSourceModule {

    @Binds
    fun bindsRegisterUserRemoteDataSource(impl: RegisterUserRemoteDataSourceImpl): RegisterUserRemoteDataSource
}