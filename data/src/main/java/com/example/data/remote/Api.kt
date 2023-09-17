package com.example.data.remote


import com.example.domain.entities.remote.User
import retrofit2.http.GET


interface Api {

    @GET("users")
    suspend fun getUser(): List<User>

}