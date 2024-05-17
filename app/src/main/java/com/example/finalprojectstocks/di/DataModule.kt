package com.example.mobileappfinal.di

import com.example.mobileappfinal.data.datastore.SharedPreferenceServiceImpl
import com.example.mobileappfinal.data.repository.CoinRepositoryImpl
import com.example.mobileappfinal.domain.repository.CoinRepository
import com.example.mobileappfinal.domain.datastore.SharedPreferenceService
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