package com.example.domain.usecases

import com.example.domain.bored.BoredRepository

class ActivityUseCase(private val boredRepository: BoredRepository) {

    suspend operator fun invoke() = boredRepository.getActivity()

}