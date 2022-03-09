package com.eg.cars.main.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun BackNavigateTopBar(onBackClick: (() -> Unit)? = null) {
    IconButton(onClick = {
        onBackClick?.invoke()
    }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null
        )
    }
}