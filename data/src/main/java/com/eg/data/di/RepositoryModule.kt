package com.eg.data.di

import com.eg.data.repository.CarsRepositoryImpl
import com.eg.domain.CarsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsRepository(repository: CarsRepositoryImpl): CarsRepository
}