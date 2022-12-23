package com.example.androidbase.data


import com.example.androidbase.domain.LocalDataSource
import com.example.androidbase.domain.RemoteDataSource
import com.example.androidbase.domain.entities.remote.UsersResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getUser(): UsersResponse = remoteDataSource.getUser()
}