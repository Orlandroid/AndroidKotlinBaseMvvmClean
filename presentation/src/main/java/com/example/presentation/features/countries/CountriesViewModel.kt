package com.example.presentation.features.countries

import androidx.lifecycle.viewModelScope
import com.example.data.di.CoroutineDispatchers
import com.example.domain.countries.CountriesRepository
import com.example.domain.countries.CountryResponse
import com.example.domain.state.Result
import com.example.presentation.base.BaseViewModel
import com.example.presentation.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val repository: CountriesRepository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val _countries = MutableStateFlow<Result<List<CountryResponse>>>(Result.Loading)
    val countries = _countries


    private val _country = MutableStateFlow<Result<List<CountryResponse>>>(Result.Loading)
    val country = _country


    fun getCountries() = viewModelScope.launch {
        safeApiCall(_countries, coroutineDispatchers) {
            val response = repository.getAllCountries()
            withContext(Dispatchers.Main) {
                _countries.emit(Result.Success(response))
            }
        }
    }

    fun getCountryByName(name: String) = viewModelScope.launch {
        safeApiCall(_country, coroutineDispatchers) {
            delay(1.seconds)
            val response = repository.getCountryByName(name)
            withContext(Dispatchers.Main) {
                _country.emit(Result.Success(response))
            }
        }
    }
}
