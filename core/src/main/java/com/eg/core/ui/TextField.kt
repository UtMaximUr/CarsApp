package com.eg.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    onClick: ((String) -> Unit)? = null
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable(enabled = onClick != null) {
                onClick?.invoke(text)
            }
            .padding(16.dp)
            .fillMaxWidth(),
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}