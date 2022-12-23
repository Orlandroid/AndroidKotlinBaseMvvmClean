package com.example.androidbase.data.remote

import com.example.androidbase.domain.entities.remote.UsersResponse
import retrofit2.http.GET


interface Api {

    @GET("users")
    suspend fun getUser(): UsersResponse

}