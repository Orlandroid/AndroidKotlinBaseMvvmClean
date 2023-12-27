package com.example.data.bored


import com.example.domain.bored.ActivityResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface BoredApi {

    @GET("activity")
    suspend fun getActivity(): ActivityResponse

    @GET("activity")
    suspend fun getActivityByParticipants(@Query("participants") participants: String): ActivityResponse

}