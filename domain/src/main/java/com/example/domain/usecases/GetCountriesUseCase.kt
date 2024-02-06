package com.example.domain.usecases

import com.example.domain.countries.CountriesRepository

class GetCountriesUseCase(private val countriesRepository: CountriesRepository) {

    suspend operator fun invoke() = countriesRepository.getAllCountries()

}