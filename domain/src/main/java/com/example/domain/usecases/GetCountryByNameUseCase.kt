package com.example.domain.usecases

import com.example.domain.countries.CountriesRepository

class GetCountryByNameUseCase(private val countriesRepository: CountriesRepository) {

    suspend operator fun invoke(name: String) = countriesRepository.getCountryByName(name)

}