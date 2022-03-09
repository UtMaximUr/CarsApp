package com.eg.core.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    text: String,
    onBackClick: @Composable (() -> Unit)? = null
) {

    TopAppBar(
        title = {
            Text(
                text = text,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = onBackClick,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp
    )
}