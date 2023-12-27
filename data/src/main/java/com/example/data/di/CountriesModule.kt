package com.example.data.di


import com.example.data.countries.CountriesApi
import com.example.data.countries.CountriesRepositoryImpl
import com.example.domain.countries.CountriesRepository
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
object CountriesModule {


    private const val BASE_URL = "http://www.boredapi.com/api/"
    private const val COUNTRIES = "Countries"


    @Singleton
    @Provides
    @Named(COUNTRIES)
    fun provideRetrofitCountries(okHttpClient: OkHttpClient): Retrofit =
        provideRetrofitGeneric(okHttpClient, BASE_URL)

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