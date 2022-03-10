package com.eg.domain

import androidx.paging.PagingData
import com.eg.domain.entity.Brand
import com.eg.domain.entity.Model
import com.eg.domain.entity.Resource
import com.eg.domain.entity.Year
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    suspend fun fetchBrands(): Resource<Flow<PagingData<Brand>>>

    suspend fun fetchModels(id: String?): Resource<Flow<PagingData<Model>>>

    suspend fun fetchYears(id: String?, name: String?): Resource<Flow<List<Year>>>
}