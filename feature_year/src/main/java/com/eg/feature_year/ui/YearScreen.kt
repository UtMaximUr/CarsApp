package com.eg.feature_year.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eg.core.navigation.NavigationDestination
import com.eg.core.ui.BackNavigateTopBar
import com.eg.core.ui.TextField
import com.eg.core.ui.TopBar
import com.eg.core.utils.toJson
import com.eg.domain.entity.Cars
import com.eg.feature_year.R
import com.eg.feature_year.YearViewModel

@Composable
fun YearScreen(
    navController: NavHostController,
    arguments: Cars?,
    viewModel: YearViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchYear(arguments)
    }

    val years = viewModel.years.collectAsState(listOf())

    Column {
        TopBar(text = stringResource(id = R.string.select_year), onBackClick = {
            BackNavigateTopBar {
                navController.popBackStack()
            }
        })
        Spacer(modifier = Modifier.height(16.dp))
        arguments?.brand?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
        arguments?.model?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
        LazyColumn {
            items(years.value) { item ->
                item.year?.let {
                    TextField(text = it) { year ->
                        navController.navigate(
                            NavigationDestination.InfoScreen.route.plus(
                                "/${
                                    arguments?.copy(
                                        year = year
                                    ).toJson()
                                }"
                            )
                        )
                    }
                }
            }
        }
    }
}