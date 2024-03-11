package com.example.rickmorty.presenter.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.rickmorty.R
import com.example.rickmorty.data.model.GetAllCharactersModelDto
import com.example.rickmorty.utils.StatusToIcon


@OptIn(
    ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
fun DetailsScreen(
    selectedCharacter: GetAllCharactersModelDto.Results?,
    imageHeight: Dp,
    bottomSheetHeight: Dp,
    isVisible: MutableState<Boolean>,
    modalState: SheetState,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        selectedCharacter?.let { chars ->
            GlideImage(
                model = chars.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = placeholder(R.drawable.place_holder),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(30.dp))
            )
            AnimatedVisibility(
                visible = isVisible.value
            ) {
                ModalBottomSheet(
                    onDismissRequest = {
                        isVisible.value = false
                        onDismiss()
                    },
                    sheetState = modalState,
                    scrimColor = Color.Transparent,
                    containerColor = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInVertically(tween(1000))
                            )
                            .fillMaxWidth()
                            .height(bottomSheetHeight)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "name")
                            Text(text = chars.name ?: "")
                        }
                        Spacer(modifier = Modifier.height(13.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "status")
                            chars.status?.StatusToIcon()
                        }
                    }
                }
            }
        }
    }
}