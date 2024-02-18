package com.example.admin.di

import com.example.admin.data.database.TugasRemoteDataSource
import com.example.admin.data.database.TugasRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TugasRemoteDataSourceModule {

    @Binds
    fun bindsTugasRemoteDataSource(impl: TugasRemoteDataSourceImpl): TugasRemoteDataSource
}