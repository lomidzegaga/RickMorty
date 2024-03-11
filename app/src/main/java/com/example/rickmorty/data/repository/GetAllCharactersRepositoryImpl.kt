package com.example.rickmorty.data.repository

import com.example.rickmorty.data.model.GetAllCharactersModelDto
import com.example.rickmorty.data.remote.ApiService
import com.example.rickmorty.domain.reposotory.GetAllCharactersRepository
import retrofit2.Response
import javax.inject.Inject

class GetAllCharactersRepositoryImpl @Inject constructor(
    private val api: ApiService
) : GetAllCharactersRepository {

    override suspend fun getAllCharacters(pages: Int): Response<GetAllCharactersModelDto> =
        api.getAllCharacters(pages)

}