package com.eg.data.repository

import okhttp3.Response

interface RemoteDataSource {

    suspend fun fetchBrands(page: Int, pageSize: Int): Response

    suspend fun fetchModels(id: String?, page: Int, pageSize: Int): Response

    suspend fun fetchYears(id: String?, name: String?): Response
}