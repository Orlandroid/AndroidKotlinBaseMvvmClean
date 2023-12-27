package com.example.data.bored

import com.example.domain.bored.BoredRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BoredRepositoryImpl @Inject constructor(private val boredApi: BoredApi) :
    BoredRepository {

    override suspend fun getActivity() = boredApi.getActivity()
    override suspend fun getActivityByParticipants(participants: String) = boredApi.getActivityByParticipants(participants)


}