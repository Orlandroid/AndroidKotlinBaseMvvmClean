package com.example.data.countries

import com.example.domain.countries.CountriesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepositoryImpl @Inject constructor(private val countriesApi: CountriesApi) :
    CountriesRepository {
    override suspend fun getAllCountries() = countriesApi.getAllCountries()

    override suspend fun getCountryByName(name: String) = countriesApi.getCountryByName(name)

}