package com.eg.cars.data.di

import com.eg.cars.data.assets.AssetManager
import com.eg.cars.data.network.ApiDataSource
import com.eg.cars.data.remote.RemoteDataSourceImpl
import com.eg.cars.data.repository.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun apiDataSource(assetManager: AssetManager): ApiDataSource =
        ApiDataSource(assetManager)
}