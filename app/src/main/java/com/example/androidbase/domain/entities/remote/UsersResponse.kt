package com.example.androidbase.domain.entities.remote

data class UsersResponse(
    val status: Int,
    val message: String,
    val data: List<User>,
)

data class User(
    val role: String,
    val _id: String,
    val name: String,
    val email: String,
    val number: Long,
    val password: String,
    val createdAt: String,
    val updatedAt: String,
    val lastLoginAt: String,
)