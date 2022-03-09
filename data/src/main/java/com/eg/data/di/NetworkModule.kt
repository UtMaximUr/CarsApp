package com.eg.data.di

import com.eg.data.assets.AssetManager
import com.eg.data.network.ApiDataSource
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