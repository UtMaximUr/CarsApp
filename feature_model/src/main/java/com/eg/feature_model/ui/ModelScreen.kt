package com.eg.feature_model.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.eg.domain.entity.Cars
import com.eg.feature_model.ModelViewModel
import com.eg.core.ui.BackNavigateTopBar
import com.eg.core.ui.ProgressView
import com.eg.core.ui.TextField
import com.eg.core.ui.TopBar
import com.eg.core.utils.toJson
import com.eg.feature_model.R
import com.eg.core.navigation.NavigationDestination

@Composable
fun ModelScreen(
    navController: NavHostController,
    arguments: Cars?,
    viewModel: ModelViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchModel(arguments)
    }

    val models = viewModel.models.collectAsLazyPagingItems()

    Column {
        TopBar(text = stringResource(id = R.string.select_model), onBackClick = {
            BackNavigateTopBar {
                navController.popBackStack()
            }
        })
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            ProgressView(viewModel)
            Column {
                arguments?.brand?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
                LazyColumn {
                    items(models) { item ->
                        TextField(text = item?.name.toString()) { model ->
                            navController.navigate(
                                NavigationDestination.YearsScreen.route.plus(
                                    "/${
                                        arguments?.copy(model = model).toJson()
                                    }"
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}