package com.eg.cars.domain

import androidx.paging.PagingData
import com.eg.cars.data.Resource
import com.eg.cars.domain.entity.Brand
import com.eg.cars.domain.entity.Model
import com.eg.cars.domain.entity.Year
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    suspend fun fetchBrands(): Resource<Flow<PagingData<Brand>>>

    suspend fun fetchModels(id: String?): Resource<Flow<PagingData<Model>>>

    suspend fun fetchYears(id: String?, name: String?): List<Year>
}