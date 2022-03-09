package com.eg.cars.feature_brand.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.eg.cars.R
import com.eg.cars.domain.entity.Cars
import com.eg.cars.feature_brand.BrandViewModel
import com.eg.cars.main.ui.ProgressView
import com.eg.cars.main.ui.TextField
import com.eg.cars.main.ui.TopBar
import com.eg.cars.navigation.NavigationDestination
import com.eg.cars.utils.toJson


@Composable
fun BrandScreen(
    navController: NavHostController,
    viewModel: BrandViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchBrands()
    }

    val brands = viewModel.brands.collectAsLazyPagingItems()

    Column {
        TopBar(text = stringResource(id = R.string.select_brand))
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            ProgressView(viewModel)
            LazyColumn {
                items(brands) { brand ->
                    TextField(text = brand?.name.toString()) { name ->
                        navController.navigate(
                            NavigationDestination.ModelsScreen.route.plus(
                                "/${Cars(id = brand?.id, brand = name).toJson()}"
                            )
                        )
                    }
                }
            }
        }
    }
}