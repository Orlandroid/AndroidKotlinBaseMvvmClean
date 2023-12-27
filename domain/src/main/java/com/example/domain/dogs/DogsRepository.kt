package com.example.domain.dogs


interface DogsRepository {
    suspend fun getAllBreeds(): GetAllBreedsResponse

    suspend fun getImagesByBreed(breed: String): GetAllImagesByBreed

    suspend fun randomImageDog(): RandomImageResponse
}