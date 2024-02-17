package com.example.admin.di

import com.example.admin.data.database.MateriRemoteDataSource
import com.example.admin.data.database.MateriRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MateriRemoteDataSourceModule {

    @Binds
    fun bindsMateriRemoteDataSource(impl: MateriRemoteDataSourceImpl): MateriRemoteDataSource
}