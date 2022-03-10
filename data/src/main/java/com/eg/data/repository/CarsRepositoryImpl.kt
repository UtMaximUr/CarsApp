package com.eg.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eg.data.BrandSource
import com.eg.data.ModelSource

import com.eg.domain.entity.Brand
import com.eg.domain.entity.Model
import com.eg.domain.entity.Year
import com.eg.data.utils.NETWORK_PAGE_SIZE
import com.eg.domain.CarsRepository
import com.eg.domain.entity.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CarsRepository {

    override suspend fun fetchBrands(): Resource<Flow<PagingData<Brand>>> {
        return try {
            Resource.success(Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
                BrandSource(remoteDataSource, NETWORK_PAGE_SIZE)
            }.flow)
        } catch (exception: Exception) {
            Resource.error()
        }
    }

    override suspend fun fetchModels(id: String?): Resource<Flow<PagingData<Model>>> {
        return try {
            Resource.success(Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
                ModelSource(id, remoteDataSource, NETWORK_PAGE_SIZE)
            }.flow)
        } catch (exception: Exception) {
            Resource.error()
        }
    }

    override suspend fun fetchYears(id: String?, name: String?): List<Year> {
        return remoteDataSource.fetchYears(id, name).years.map { it.toYear() }
    }
}