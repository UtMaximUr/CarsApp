package com.eg.cars.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.eg.cars.main.ui.theme.MySelectionOfCarsTheme
import com.eg.cars.navigation.AppNavGraph

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    MySelectionOfCarsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            AppNavGraph(navController = navController)
        }
    }
}