package com.example.rickmorty.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.rickmorty.presenter.ui.screens.DetailsScreen
import com.example.rickmorty.presenter.ui.screens.MainScreen
import com.example.rickmorty.presenter.view_model.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainScreenViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var currentPage by remember { mutableStateOf<Pages>(Pages.MainPage)}
            val backStack = remember { mutableListOf<Pages>() }
            val screenSize = LocalConfiguration.current
            val height = screenSize.screenHeightDp * 0.46
            var id by remember { mutableStateOf<Int?>(null) }

            var isLoad by remember { mutableStateOf(false) }

            val showBottomSheet = remember { mutableStateOf(false) }
            val modalState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

            if (!isLoad) {
                viewModel.charactersList = viewModel.characters.value.results ?: emptyList()
            }

            when (currentPage) {
                Pages.MainPage -> {
                    if (!viewModel.isLoaded) {
                        MainScreen(charactersList = viewModel.charactersList, onClick = {
                            currentPage = Pages.DetailsPage
                            backStack.add(Pages.MainPage)
                            showBottomSheet.value = true
                            id = it.id
                        }) {
                            if (viewModel.charactersList.size < (viewModel.characters.value.info?.count
                                    ?: 0)
                            ) {
                                isLoad = true
                                viewModel.updateCurrentPage()
                                viewModel.charactersList += viewModel.characters.value.results
                                    ?: emptyList()
                            }
                        }
                    }
                }

                Pages.DetailsPage -> {
                    id?.let {
                        val selectedItem = viewModel.charactersList.find { chars -> chars.id == it }
                        DetailsScreen(
                            selectedCharacter = selectedItem,
                            imageHeight = (screenSize.screenHeightDp * 0.46).dp,
                            bottomSheetHeight = height.dp,
                            isVisible = showBottomSheet,
                            modalState = modalState
                        ) {
                            currentPage = backStack[backStack.size - 1]
                        }
                    }
                }
            }
        }
    }
}

sealed class Pages {
    data object MainPage : Pages()
    data object DetailsPage : Pages()
}