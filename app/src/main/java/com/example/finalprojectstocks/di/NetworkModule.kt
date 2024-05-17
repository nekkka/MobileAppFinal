package com.example.finalprojectstocks.di

import android.util.Log
import com.example.finalprojectstocks.BuildConfig
import com.example.finalprojectstocks.common.Constants
import com.example.finalprojectstocks.data.network.CoinGeckoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val requestWithKey =
                it.request().newBuilder().addHeader("x-cg-demo-api-key", BuildConfig.API_KEY)
                    .build()
            Log.e("Request", requestWithKey.url.toString())
            return@addInterceptor it.proceed(requestWithKey)
        }.build()
        return okHttpClient
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.BASE_URL).build()
    }


    @Singleton
    @Provides
    fun provideCoinAPI(retrofit: Retrofit): CoinGeckoAPI {
        return retrofit.create(CoinGeckoAPI::class.java)
    }
}