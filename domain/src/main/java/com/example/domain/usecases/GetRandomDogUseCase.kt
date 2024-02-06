package com.example.domain.usecases

import com.example.domain.dogs.DogsRepository

class GetRandomDogUseCase(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke() = dogsRepository.randomImageDog()

}