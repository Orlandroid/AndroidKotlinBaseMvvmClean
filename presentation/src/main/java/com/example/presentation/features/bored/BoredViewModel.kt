package com.example.presentation.features.bored

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.di.CoroutineDispatchers
import com.example.domain.bored.ActivityResponse
import com.example.domain.bored.BoredRepository
import com.example.domain.state.Result
import com.example.presentation.base.BaseViewModel
import com.example.presentation.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BoredViewModel @Inject constructor(
    private val repository: BoredRepository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val _getActivityResponse = MutableLiveData<Result<ActivityResponse>>()
    val getActivityResponse = _getActivityResponse


    init {
        getActivity()
    }

    fun getActivity() {
        viewModelScope.launch {
            safeApiCall(_getActivityResponse, coroutineDispatchers) {
                val response = repository.getActivity()
                withContext(Dispatchers.Main) {
                    _getActivityResponse.value = Result.Success(response)
                }
            }
        }
    }
}
