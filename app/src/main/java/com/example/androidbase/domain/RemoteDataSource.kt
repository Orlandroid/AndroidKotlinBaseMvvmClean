package com.example.androidbase.domain

import com.example.androidbase.domain.entities.remote.UsersResponse


interface RemoteDataSource {

    suspend fun getUser(): UsersResponse
}