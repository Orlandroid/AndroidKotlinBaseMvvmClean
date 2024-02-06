package com.example.presentation.features.dogs

import androidx.lifecycle.viewModelScope
import com.example.data.di.CoroutineDispatchers
import com.example.domain.dogs.RandomImageResponse
import com.example.domain.state.Result
import com.example.domain.usecases.GetRandomDogUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val getRandomDogUseCase: GetRandomDogUseCase,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val _getRandomImage = MutableStateFlow<Result<RandomImageResponse>>(Result.Loading)
    val getRandomImage = _getRandomImage

    fun getRandomImage() = viewModelScope.launch {
        safeApiCall(_getRandomImage, coroutineDispatchers) {
            val response = getRandomDogUseCase()
            withContext(Dispatchers.Main) {
                _getRandomImage.value = Result.Success(response)
            }
        }
    }
}
