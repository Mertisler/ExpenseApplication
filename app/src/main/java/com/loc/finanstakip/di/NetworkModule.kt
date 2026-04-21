package com.loc.finanstakip.di

import com.loc.finanstakip.data.network.ExpenseApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://69e524f0cfa9394db8dabe63.mockapi.io/api/v1/:endpoint")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Provides
    @Singleton
    fun provideExpenseApiService(retrofit: Retrofit): ExpenseApiService {
        return retrofit.create(ExpenseApiService::class.java)
    }
}