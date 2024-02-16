package com.example.my_e_learning.data.database

import com.example.my_e_learning.data.database.RegisterUserRemoteDataSource
import com.example.my_e_learning.data.database.RegisterUserRemoteDataSourceImpl
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