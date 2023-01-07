package com.example.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: String,
    val role: String,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: String,
    val updatedAt: String,
    val lastLoginAt: String
)
