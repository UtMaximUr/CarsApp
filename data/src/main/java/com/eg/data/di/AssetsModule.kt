package com.eg.data.di

import android.content.Context
import com.eg.data.assets.AssetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AssetsModule {

    @Singleton
    @Provides
    fun providesAssetsManager(@ApplicationContext context: Context) = AssetManager(context.assets)
}