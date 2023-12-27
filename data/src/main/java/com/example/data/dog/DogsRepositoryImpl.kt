package com.example.data.dog

import com.example.domain.dogs.DogsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsRepositoryImpl @Inject constructor(private val dogsApi: DogsApi) :
    DogsRepository {
    override suspend fun getAllBreeds() = dogsApi.getAllBreeds()

    override suspend fun getImagesByBreed(breed: String) = dogsApi.getImagesByBreed(breed)

    override suspend fun randomImageDog() = dogsApi.randomImageDog()

}