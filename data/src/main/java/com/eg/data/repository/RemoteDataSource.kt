package com.eg.data.repository


import com.eg.data.network.entity.BrandResponse
import com.eg.data.network.entity.ModelsResponse
import com.eg.data.network.entity.YearsResponse


interface RemoteDataSource {

    suspend fun fetchBrands(page: Int, pageSize: Int): BrandResponse

    suspend fun fetchModels(id: String?, page: Int, pageSize: Int): ModelsResponse

    suspend fun fetchYears(id: String?, name: String?): YearsResponse
}