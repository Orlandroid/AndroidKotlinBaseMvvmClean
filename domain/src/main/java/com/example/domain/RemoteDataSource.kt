package com.example.domain

import com.example.domain.entities.remote.UsersResponse


interface RemoteDataSource {

    suspend fun getUser(): UsersResponse
}