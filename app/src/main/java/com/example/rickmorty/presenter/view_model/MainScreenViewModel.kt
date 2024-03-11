package com.example.rickmorty.presenter.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.model.GetAllCharactersModelDto
import com.example.rickmorty.domain.reposotory.GetAllCharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: GetAllCharactersRepository
) : ViewModel() {

    private var currentPage = 1

    private val _characters = MutableStateFlow(GetAllCharactersModelDto())
    val characters = _characters.asStateFlow()

    var charactersList by mutableStateOf(characters.value.results ?: emptyList())

    init {
        getAllCharacters()
    }

    var isLoaded by mutableStateOf(true)
        private set

    private fun getAllCharacters() {
        viewModelScope.launch {
            val resp = repository.getAllCharacters(currentPage)
            if (resp.isSuccessful) {
                _characters.value = resp.body() ?: GetAllCharactersModelDto()
                isLoaded = false
            } else {
                Log.d("!!!! error", resp.errorBody().toString())
            }
        }
    }

    fun updateCurrentPage() {
        currentPage += 1
        getAllCharacters()
    }
}