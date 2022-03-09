package com.eg.core.ui

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.eg.cars.main.base.BaseViewModel

@Composable
fun ProgressView(viewModel: BaseViewModel) {

    val progressState = viewModel.progressState.observeAsState(true)
    val errorState = viewModel.errorState.observeAsState()

    if (progressState.value) {
        CircularProgressIndicator()
    }
    TextField(text = errorState.value ?: "")
}