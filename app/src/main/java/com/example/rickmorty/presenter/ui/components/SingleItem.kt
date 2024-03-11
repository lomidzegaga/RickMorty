package com.example.rickmorty.presenter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.rickmorty.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SingleItem(
    imageUrl: String,
    characterName: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .padding(15.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 3.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = placeholder(R.drawable.place_holder),
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = characterName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(0.7f)
                )
                Image(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }
}