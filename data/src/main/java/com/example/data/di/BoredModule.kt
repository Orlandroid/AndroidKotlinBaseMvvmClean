package com.example.data.di


import com.example.data.bored.BoredApi
import com.example.data.bored.BoredRepositoryImpl
import com.example.domain.bored.BoredRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BoredModule {


    private const val BASE_URL = "http://www.boredapi.com/api/"
    private const val BORED = "Bored"


    @Singleton
    @Provides
    @Named(BORED)
    fun provideRetrofitBored(okHttpClient: OkHttpClient): Retrofit = provideRetrofitGeneric(
        okHttpClient,
        BASE_URL
    )

    @Singleton
    @Provides
    fun provideBoredApi(@Named(BORED) retrofit: Retrofit): BoredApi =
        retrofit.create(BoredApi::class.java)

    @Provides
    @Singleton
    fun provideBoredRepository(
        boredApi: BoredApi,
    ): BoredRepository = BoredRepositoryImpl(boredApi)


}