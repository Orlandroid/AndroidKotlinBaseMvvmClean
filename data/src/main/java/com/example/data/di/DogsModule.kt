package com.example.data.di


import com.example.data.bored.BoredApi
import com.example.data.bored.BoredRepositoryImpl
import com.example.data.dog.DogsApi
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
object DogsModule {


    private const val BASE_URL = "http://www.boredapi.com/api/"
    private const val DOGS = "Dogs"


    @Singleton
    @Provides
    @Named(DOGS)
    fun provideRetrofitDogs(okHttpClient: OkHttpClient): Retrofit =
        provideRetrofitGeneric(okHttpClient, BASE_URL)

    @Singleton
    @Provides
    fun provideDogsApi(@Named(DOGS) retrofit: Retrofit) = retrofit.create(DogsApi::class.java)

    @Provides
    @Singleton
    fun provideDogsRepository(
        boredApi: BoredApi,
    ): BoredRepositoryImpl = BoredRepositoryImpl(boredApi)


}