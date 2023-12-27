package com.example.data.di


import com.example.data.dog.DogsApi
import com.example.data.dog.DogsRepositoryImpl
import com.example.domain.dogs.DogsRepository
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


    private const val BASE_URL = "https://dog.ceo/api/"
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
        dogsApi: DogsApi,
    ): DogsRepository = DogsRepositoryImpl(dogsApi)


}