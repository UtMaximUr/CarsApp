package com.eg.cars.data.repository


import com.eg.cars.data.network.entity.BrandResponse
import com.eg.cars.data.network.entity.CarsResponse
import com.eg.cars.data.network.entity.ModelsResponse
import com.eg.cars.data.network.entity.YearsResponse


interface RemoteDataSource {

    suspend fun fetchBrands(page: Int): BrandResponse

    suspend fun fetchModels(id: String?, page: Int): ModelsResponse

    suspend fun fetchYears(id: String?, name: String?): YearsResponse
}