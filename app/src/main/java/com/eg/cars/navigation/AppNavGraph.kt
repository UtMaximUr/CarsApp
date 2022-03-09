package com.eg.cars.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eg.cars.feature_brand.ui.BrandScreen
import com.eg.cars.feature_info.ui.InfoScreen
import com.eg.cars.feature_model.ui.ModelScreen
import com.eg.cars.feature_year.ui.YearScreen
import com.eg.cars.utils.BRAND
import com.eg.cars.utils.MODEL
import com.eg.cars.utils.ParamType
import com.eg.cars.utils.YEAR

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.BrandsScreen.route
    ) {
        composable(NavigationDestination.BrandsScreen.route) { BrandScreen(navController = navController) }
        composable(
            NavigationDestination.ModelsScreen.route.plus("/{$BRAND}"), arguments = listOf(
                navArgument(BRAND) {
                    type = ParamType()
                }
            )) {
            ModelScreen(
                navController = navController,
                arguments = it.arguments?.getParcelable(BRAND)
            )
        }
        composable(
            NavigationDestination.YearsScreen.route.plus("/{$MODEL}"), arguments = listOf(
                navArgument(MODEL) {
                    type = ParamType()
                }
            )) {
            YearScreen(
                navController = navController,
                arguments = it.arguments?.getParcelable(MODEL)
            )
        }
        composable(
            NavigationDestination.InfoScreen.route.plus("/{$YEAR}"), arguments = listOf(
                navArgument(YEAR) {
                    type = ParamType()
                }
            )) {
            InfoScreen(
                navController = navController,
                arguments = it.arguments?.getParcelable(YEAR)
            )
        }
    }
}