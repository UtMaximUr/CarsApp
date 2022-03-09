package com.eg.data.remote

import com.eg.data.network.ApiDataSource
import com.eg.data.network.entity.BrandResponse
import com.eg.data.network.entity.ModelsResponse
import com.eg.data.network.entity.YearsResponse
import com.eg.data.repository.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RemoteDataSourceImpl(private val api: ApiDataSource) : RemoteDataSource {

    override suspend fun fetchBrands(page: Int): BrandResponse = withContext(Dispatchers.IO) {
        api.loadBrands(page)
    }

    override suspend fun fetchModels(id: String?, page: Int): ModelsResponse =
        withContext(Dispatchers.IO) {
            api.loadModels(id, page)
        }

    override suspend fun fetchYears(id: String?, name: String?): YearsResponse =
        withContext(Dispatchers.IO) {
            api.loadYears(id, name)
        }
}