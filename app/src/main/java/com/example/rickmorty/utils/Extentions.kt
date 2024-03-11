package com.example.rickmorty.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.rickmorty.presenter.ui.components.StatusCircle

@Composable
fun String.StatusToIcon() = when (this) {
    "Alive" -> StatusCircle(color = Color.Green)
    "Dead" -> StatusCircle(color = Color.Red)
    "unknown" -> StatusCircle(color = Color.Gray)
    else -> StatusCircle(color = Color.Transparent)
}