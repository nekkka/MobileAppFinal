package com.example.finalprojectstocks.di

import com.example.finalprojectstocks.domain.datastore.SharedPreferenceService
import com.example.finalprojectstocks.domain.repository.CoinRepository
import com.example.finalprojectstocks.data.datastore.SharedPreferenceServiceImpl
import com.example.finalprojectstocks.data.repository.CoinRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {


    @Binds
    @Singleton
    abstract fun bindRepository(repository: CoinRepositoryImpl): CoinRepository

    @Binds
    @Singleton
    abstract fun providePreferenceServices(sharedPreferenceServiceImpl: SharedPreferenceServiceImpl): SharedPreferenceService
}