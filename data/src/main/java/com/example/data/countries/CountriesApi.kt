package com.example.data.countries


import com.example.domain.countries.CountryResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface CountriesApi {

    @GET("all")
    suspend fun getAllCountries(): List<CountryResponse>

    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") countryName: String): List<CountryResponse>

}