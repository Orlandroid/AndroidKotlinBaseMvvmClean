package com.example.data.dog


import com.example.domain.dogs.GetAllBreedsResponse
import com.example.domain.dogs.GetAllImagesByBreed
import com.example.domain.dogs.RandomImageResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface DogsApi {

    //https://dog.ceo/api/breeds/list/all
    @GET("list/all")
    suspend fun getAllBreeds(): GetAllBreedsResponse


    //https://dog.ceo/api/breed/hound/images
    @GET("hound/images")
    suspend fun getImagesByBreed(
        @Path("breed") breed: String
    ): GetAllImagesByBreed

    //https://dog.ceo/api/breeds/image/random
    @GET("image/random")
    suspend fun randomImageDog(): RandomImageResponse
}