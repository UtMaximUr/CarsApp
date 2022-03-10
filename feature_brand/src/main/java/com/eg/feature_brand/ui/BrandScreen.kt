package com.eg.feature_brand.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.eg.domain.entity.Cars
import com.eg.feature_brand.BrandViewModel
import com.eg.core.ui.ProgressView
import com.eg.core.ui.TextField
import com.eg.core.ui.TopBar
import com.eg.core.utils.toJson
import com.eg.feature_brand.R
import com.eg.core.navigation.NavigationDestination


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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ProgressView(viewModel)
        }
    }
}