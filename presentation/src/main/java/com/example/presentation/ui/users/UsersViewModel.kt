package com.example.presentation.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.Repository
import com.example.presentation.base.BaseViewModel
import com.example.presentation.helpers.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.domain.state.Result
import com.example.data.di.CoroutineDispatchers
import com.example.domain.entities.remote.UsersResponse
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: Repository,
    coroutineDispatchers: CoroutineDispatchers,
    networkHelper: NetworkHelper
) : BaseViewModel(coroutineDispatchers, networkHelper) {


    private val _userResponse = MutableLiveData<Result<UsersResponse>>()
    val userResponse: LiveData<Result<UsersResponse>>
        get() = _userResponse

    fun getUsers() {
        viewModelScope.launch {
            safeApiCall(_userResponse, coroutineDispatchers) {
                val response = repository.getUser()
                withContext(Dispatchers.Main) {
                    _userResponse.value = Result.Success(response)
                }
            }
        }
    }

}