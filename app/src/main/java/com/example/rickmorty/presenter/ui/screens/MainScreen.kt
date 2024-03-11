package com.example.rickmorty.presenter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.rickmorty.R
import com.example.rickmorty.data.model.GetAllCharactersModelDto
import com.example.rickmorty.presenter.ui.components.SingleItem

@Composable
fun MainScreen(
    charactersList: List<GetAllCharactersModelDto.Results>,
    onClick: (GetAllCharactersModelDto.Results) -> Unit,
    bottomReached: () -> Unit,
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_morty_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.TopCenter
        )
        LazyColumn {
            itemsIndexed(charactersList) { _, item ->
                SingleItem(
                    imageUrl = item.image ?: "",
                    characterName = item.name ?: "",
                    onClick = { onClick(item) }
                )
            }

            item { LaunchedEffect(key1 = true) { bottomReached.invoke() } }
        }
    }
}