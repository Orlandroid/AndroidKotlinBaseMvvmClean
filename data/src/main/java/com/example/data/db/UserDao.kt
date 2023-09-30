package com.example.data.db

import androidx.room.*
import com.example.data.db.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM User")
    fun getAllUser(): Flow<List<User>>

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE id =:id")
    fun getUserById(id: Int): Flow<User>

    @Query("DELETE  FROM user")
    suspend fun deleteAll()

}