package com.example.domain.dogs

import kotlinx.serialization.Serializable

@Serializable
data class GetAllImagesByBreed(
    val message: List<String>, val status: String
)