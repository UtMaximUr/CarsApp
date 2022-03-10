package com.eg.data.remote

import com.eg.data.network.ApiDataSource
import com.eg.data.network.entity.BrandResponse
import com.eg.data.network.entity.ModelsResponse
import com.eg.data.network.entity.YearsResponse
import com.eg.data.repository.RemoteDataSource


class RemoteDataSourceImpl(private val api: ApiDataSource) : RemoteDataSource {

    override suspend fun fetchBrands(page: Int, pageSize: Int): BrandResponse {
        return api.loadBrands(page, pageSize)
    }

    override suspend fun fetchModels(id: String?, page: Int, pageSize: Int): ModelsResponse {
        return api.loadModels(id, page, pageSize)
    }

    override suspend fun fetchYears(id: String?, name: String?): YearsResponse {
        return api.loadYears(id, name)
    }
}