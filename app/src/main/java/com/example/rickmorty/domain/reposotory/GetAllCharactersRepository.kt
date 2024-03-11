package com.example.rickmorty.domain.reposotory

import com.example.rickmorty.data.model.GetAllCharactersModelDto
import retrofit2.Response

interface GetAllCharactersRepository {

    suspend fun getAllCharacters(pages: Int): Response<GetAllCharactersModelDto>

}