package com.example.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.entities.User


@Database(entities = [User::class], version = 1, exportSchema = false)

abstract class MainDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}