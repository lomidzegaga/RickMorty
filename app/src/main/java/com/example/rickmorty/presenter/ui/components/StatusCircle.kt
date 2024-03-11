package com.example.rickmorty.presenter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusCircle(color: Color) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(color)
    )
}