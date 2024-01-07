package com.example.data.di


import com.example.data.dog.DogsApi
import com.example.data.dog.DogsRepositoryImpl
import com.example.domain.dogs.DogsRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogsModule {


    private const val BASE_URL = "https://dog.ceo/api/"
    private const val DOGS = "Dogs"


    @Singleton
    @Provides
    @Named(DOGS)
    fun provideRetrofitDogs(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(ModuleApi.contentType))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideDogsApi(@Named(DOGS) retrofit: Retrofit) = retrofit.create(DogsApi::class.java)

    @Provides
    @Singleton
    fun provideDogsRepository(
        dogsApi: DogsApi,
    ): DogsRepository = DogsRepositoryImpl(dogsApi)


}