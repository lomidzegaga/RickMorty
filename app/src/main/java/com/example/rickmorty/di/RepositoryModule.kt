package com.example.rickmorty.di

import com.example.rickmorty.data.repository.GetAllCharactersRepositoryImpl
import com.example.rickmorty.domain.reposotory.GetAllCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(getAllCharactersImpl: GetAllCharactersRepositoryImpl): GetAllCharactersRepository

}