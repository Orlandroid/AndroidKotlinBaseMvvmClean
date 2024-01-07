package com.example.domain.countries

import kotlinx.serialization.Serializable

@Serializable
data class MyCountryResponse(
    val name: Name,
    //val currencies: Currencies,
    val capital: List<String>,
    //val capitalInfo: CapitalInfo,
    val population: Int,
    val flags: Flags
)