package com.example.androidbase.data.local

import com.example.androidbase.data.db.UserDao
import com.example.androidbase.domain.LocalDataSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
) : LocalDataSource {

}
