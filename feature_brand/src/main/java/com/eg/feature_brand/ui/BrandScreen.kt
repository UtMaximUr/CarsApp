package com.eg.feature_brand.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.eg.core.navigation.NavigationDestination
import com.eg.core.ui.*
import com.eg.core.utils.toJson
import com.eg.domain.entity.Cars
import com.eg.feature_brand.BrandViewModel
import com.eg.feature_brand.R


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
            brands.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = brands.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage,
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = brands.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage,
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}