package com.eg.data.remote

import com.eg.data.network.ApiDataSource
import com.eg.data.repository.RemoteDataSource
import okhttp3.Response


class RemoteDataSourceImpl(private val api: ApiDataSource) : RemoteDataSource {

    override suspend fun fetchBrands(page: Int, pageSize: Int): Response  {
        return api.loadBrands(page, pageSize)
    }

    override suspend fun fetchModels(id: String?, page: Int, pageSize: Int): Response {
        return api.loadModels(id, page, pageSize)
    }

    override suspend fun fetchYears(id: String?, name: String?): Response {
        return api.loadYears(id, name)
    }
}