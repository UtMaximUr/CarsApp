package com.eg.cars.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eg.cars.data.BrandSource
import com.eg.cars.data.ModelSource
import com.eg.cars.data.Resource
import com.eg.cars.domain.entity.Brand
import com.eg.cars.domain.entity.Model
import com.eg.cars.domain.entity.Year
import com.eg.cars.domain.CarsRepository
import com.eg.cars.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CarsRepository {

    override suspend fun fetchBrands(): Resource<Flow<PagingData<Brand>>> {
        return try {
            Resource.success(Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
                BrandSource(remoteDataSource)
            }.flow)
        } catch (exception: Exception) {
            Resource.error()
        }
    }

    override suspend fun fetchModels(id: String?): Resource<Flow<PagingData<Model>>> {
        return try {
            Resource.success(Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
                ModelSource(id, remoteDataSource)
            }.flow)
        } catch (exception: Exception) {
            Resource.error()
        }
    }

    override suspend fun fetchYears(id: String?, name: String?): List<Year> {
        return remoteDataSource.fetchYears(id, name).years.map { it.toYear() }
    }
}