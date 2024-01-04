package com.example.presentation.features.bored

import androidx.lifecycle.viewModelScope
import com.example.data.di.CoroutineDispatchers
import com.example.domain.bored.ActivityResponse
import com.example.domain.bored.BoredRepository
import com.example.domain.state.Result
import com.example.presentation.base.BaseViewModel
import com.example.presentation.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BoredViewModel @Inject constructor(
    private val repository: BoredRepository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {

    private val _getActivityResponse = MutableStateFlow<Result<ActivityResponse>>(Result.Loading)
    val getActivityResponse: StateFlow<Result<ActivityResponse>> = _getActivityResponse.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Result.Loading
    )


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
