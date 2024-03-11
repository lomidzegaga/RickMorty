package com.example.rickmorty.data.remote

import com.example.rickmorty.data.model.GetAllCharactersModelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<GetAllCharactersModelDto>

}