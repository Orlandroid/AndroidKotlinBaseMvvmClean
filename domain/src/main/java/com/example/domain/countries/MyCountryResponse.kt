package com.example.domain.countries

data class MyCountryResponse(
    val name: Name,
    //val currencies: Currencies,
    val capital: List<String>,
    //val capitalInfo: CapitalInfo,
    val population: Int,
    val flags: Flags
)