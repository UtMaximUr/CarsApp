package com.eg.cars.navigation


sealed class NavigationDestination(val route: String) {
    object BrandsScreen :
        NavigationDestination("brands_screen")

    object ModelsScreen :
        NavigationDestination("models_screen")

    object YearsScreen :
        NavigationDestination("years_screen")

    object InfoScreen :
        NavigationDestination(route = "info_screen")

}