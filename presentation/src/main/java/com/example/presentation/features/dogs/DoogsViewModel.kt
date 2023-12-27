package com.example.presentation.features.dogs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.di.CoroutineDispatchers
import com.example.domain.bored.ActivityResponse
import com.example.domain.bored.BoredRepository
import com.example.domain.dogs.DogsRepository
import com.example.domain.dogs.RandomImageResponse
import com.example.domain.state.Result
import com.example.presentation.base.BaseViewModel
import com.example.presentation.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DoogsViewModel @Inject constructor(
    private val repository: DogsRepository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val _getRandomImage = MutableLiveData<Result<RandomImageResponse>>()
    val getRandomImage = _getRandomImage

    fun getRandomImage() {
        viewModelScope.launch {
            safeApiCall(_getRandomImage, coroutineDispatchers) {
                val response = repository.randomImageDog()
                withContext(Dispatchers.Main) {
                    _getRandomImage.value = Result.Success(response)
                }
            }
        }
    }
}
