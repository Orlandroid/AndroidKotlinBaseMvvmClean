package com.example.data.di


import com.example.data.countries.CountriesApi
import com.example.data.countries.CountriesRepositoryImpl
import com.example.domain.countries.CountriesRepository
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
object CountriesModule {


    private const val BASE_URL = "https://restcountries.com/v3.1/"
    private const val COUNTRIES = "Countries"


    @Singleton
    @Provides
    @Named(COUNTRIES)
    fun provideRetrofitCountries(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(ModuleApi.contentType))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideCountriesApi(@Named(COUNTRIES) retrofit: Retrofit) =
        retrofit.create(CountriesApi::class.java)

    @Provides
    @Singleton
    fun provideCountriesRepository(
        countriesApi: CountriesApi,
    ): CountriesRepository = CountriesRepositoryImpl(countriesApi)


}