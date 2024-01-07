package com.example.domain.countries

import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    val altSpellings: List<String>,
    val area: Double,
    val capital: List<String> = emptyList(),
    val capitalInfo: CapitalInfo,
    val car: Car,
    val cca2: String,
    val cca3: String,
    val ccn3: String? = null,
    val coatOfArms: CoatOfArms,
    val continents: List<String>,
    val currencies: Currencies? = null,
    val demonyms: Demonyms? = null,
    val flag: String,
    val flags: Flags,
    val idd: Idd,
    val independent: Boolean? = null,
    val landlocked: Boolean,
    val languages: Languages? = null,
    val latlng: List<Double> = emptyList(),
    val maps: Maps,
    val name: Name,
    val population: Int,
    val postalCode: PostalCode? = null,
    val region: String,
    val startOfWeek: String,
    val status: String,
    val subregion: String? = null,
    val timezones: List<String>,
    val tld: List<String> = emptyList(),
    val translations: Translations,
    val unMember: Boolean
)

@Serializable
data class CapitalInfo(
    val latlng: List<Double> = emptyList()
)

@Serializable
data class Car(
    val side: String,
    val signs: List<String> = emptyList()
)

@Serializable
data class CoatOfArms(
    val png: String? = null,
    val svg: String? = null
)

@Serializable
data class Demonyms(
    val eng: Eng
)

@Serializable
data class Flags(
    val png: String,
    val svg: String
)

@Serializable
data class Idd(
    val root: String? = null,
    val suffixes: List<String> = emptyList()
)

@Serializable
data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
)

@Serializable
data class Name(
    val common: String,
    val nativeName: NativeName? = null,
    val official: String
)

@Serializable
data class PostalCode(
    val format: String,
    val regex: String? = null
)

@Serializable
data class Translations(
    val ara: Ara? = null,
    val bre: Bre? = null,
    val ces: Ces? = null,
    val cym: Cym? = null,
    val deu: Deu? = null,
    val est: Est? = null,
    val fin: Fin? = null,
    val fra: Fra? = null,
    val hrv: Hrv? = null,
    val hun: Hun? = null,
    val ita: Ita? = null,
    val jpn: Jpn? = null,
    val kor: Kor? = null,
    val nld: Nld? = null,
    val per: Per? = null,
    val pol: Pol? = null,
    val por: Por? = null,
    val rus: Rus? = null,
    val slk: Slk? = null,
    val spa: Spa? = null,
    val srp: Srp? = null,
    val swe: Swe? = null,
    val tur: Tur? = null,
    val urd: Urd? = null,
    val zho: Zho? = null
)

@Serializable
data class Eng(
    val f: String,
    val m: String
)

@Serializable
data class NativeName(
    val eng: EngX? = null
)

@Serializable
data class EngX(
    val common: String,
    val official: String
)

@Serializable
data class Ara(
    val common: String,
    val official: String
)

@Serializable
data class Bre(
    val common: String,
    val official: String
)

@Serializable
data class Ces(
    val common: String,
    val official: String
)

@Serializable
data class Cym(
    val common: String,
    val official: String
)

@Serializable
data class Deu(
    val common: String,
    val official: String
)

@Serializable
data class Est(
    val common: String,
    val official: String
)

@Serializable
data class Fin(
    val common: String,
    val official: String
)

@Serializable
data class Fra(
    val common: String,
    val official: String
)

@Serializable
data class Hrv(
    val common: String,
    val official: String
)

@Serializable
data class Hun(
    val common: String,
    val official: String
)

@Serializable
data class Ita(
    val common: String,
    val official: String
)

@Serializable
data class Jpn(
    val common: String,
    val official: String
)

@Serializable
data class Kor(
    val common: String,
    val official: String
)

@Serializable
data class Nld(
    val common: String,
    val official: String
)

@Serializable
data class Per(
    val common: String,
    val official: String
)

@Serializable
data class Pol(
    val common: String,
    val official: String
)

@Serializable
data class Por(
    val common: String,
    val official: String
)

@Serializable
data class Rus(
    val common: String,
    val official: String
)

@Serializable
data class Slk(
    val common: String,
    val official: String
)

@Serializable
data class Spa(
    val common: String,
    val official: String
)

@Serializable
data class Srp(
    val common: String,
    val official: String
)

@Serializable
data class Swe(
    val common: String,
    val official: String
)

@Serializable
data class Tur(
    val common: String,
    val official: String
)

@Serializable
data class Urd(
    val common: String,
    val official: String
)

@Serializable
data class Zho(
    val common: String,
    val official: String
)