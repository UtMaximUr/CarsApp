package com.eg.feature_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eg.domain.entity.Cars
import com.eg.core.ui.BackNavigateTopBar
import com.eg.core.ui.TextField
import com.eg.core.ui.TopBar


@Composable
fun InfoScreen(
    navController: NavHostController,
    arguments: Cars?
) {
    Column {
        TopBar(text = stringResource(id = R.string.info_car), onBackClick = {
            BackNavigateTopBar {
                navController.popBackStack()
            }
        })
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            arguments?.brand?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
            arguments?.model?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
            arguments?.year?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
        }
    }
}

