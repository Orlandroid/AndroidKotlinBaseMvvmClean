package com.example.domain

import com.example.domain.entities.remote.User


interface RemoteDataSource {

    suspend fun getUser(): List<User>
}