package com.example.domain.countries


interface CountriesRepository {
    suspend fun getAllCountries(): List<CountryResponse>
    suspend fun getCountryByName(name:String): CountryResponse
}