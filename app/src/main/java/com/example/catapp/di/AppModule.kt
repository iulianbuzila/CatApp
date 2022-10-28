package com.example.catapp.di

import com.example.catapp.network.ApiService
import com.example.catapp.network.ApiServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiServiceProvider.getClient()
    }
}