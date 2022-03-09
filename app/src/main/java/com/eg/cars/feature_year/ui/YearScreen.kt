package com.eg.cars.feature_year.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eg.cars.R
import com.eg.cars.domain.entity.Cars
import com.eg.cars.feature_year.YearViewModel
import com.eg.cars.navigation.NavigationDestination
import com.eg.cars.main.ui.BackNavigateTopBar
import com.eg.cars.main.ui.TextField
import com.eg.cars.main.ui.TopBar
import com.eg.cars.utils.toJson

@Composable
fun YearScreen(
    navController: NavHostController,
    arguments: Cars?,
    viewModel: YearViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchYear(arguments)
    }

    val years by viewModel.years.observeAsState(listOf())

    Column {
        TopBar(text = stringResource(id = R.string.select_year), onBackClick = {
            BackNavigateTopBar {
                navController.popBackStack()
            }
        })
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            arguments?.brand?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
            arguments?.model?.let { TextField(text = it, fontWeight = FontWeight.Bold) }
            LazyColumn {
                items(years.size) { index ->
                    years[index].year?.let {
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
}