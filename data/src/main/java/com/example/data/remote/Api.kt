package com.example.data.remote


import com.example.domain.entities.remote.UsersResponse
import retrofit2.http.GET


interface Api {

    @GET("users")
    suspend fun getUser(): UsersResponse

}