package com.eg.data.di

import com.eg.data.network.ApiDataSource
import com.eg.data.remote.RemoteDataSourceImpl
import com.eg.data.repository.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {

    @Singleton
    @Provides
    fun remoteDataSource(apiDataSource: ApiDataSource): RemoteDataSource =
        RemoteDataSourceImpl(apiDataSource)
}