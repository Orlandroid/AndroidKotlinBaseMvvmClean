package com.example.domain.entities.remote


data class User(
    val role: String,
    val _id: String,
    val name: Name,
    val email: String,
    val number: Long,
    val password: String,
    val createdAt: String,
    val updatedAt: String,
    val lastLoginAt: String,
)

data class Name(
    val firstname: String,
    val lastname: String
) {
    fun fullName() = "$firstname $lastname"
}