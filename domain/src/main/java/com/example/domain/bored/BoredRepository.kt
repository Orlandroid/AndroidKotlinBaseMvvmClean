package com.example.domain.bored


interface BoredRepository {
    suspend fun getActivity(): ActivityResponse
    suspend fun getActivityByParticipants(participants: String): ActivityResponse
}