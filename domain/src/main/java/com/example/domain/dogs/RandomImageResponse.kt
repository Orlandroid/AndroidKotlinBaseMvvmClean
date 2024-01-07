package com.example.domain.dogs

import kotlinx.serialization.Serializable

@Serializable
data class RandomImageResponse(
    val message: String, val status: String
)