package com.eg.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eg.data.paged.BrandSource
import com.eg.data.paged.ModelSource
import com.eg.data.utils.NETWORK_PAGE_SIZE
import com.eg.domain.CarsRepository
import com.eg.domain.entity.Brand
import com.eg.domain.entity.Model
import com.eg.domain.entity.Year
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CarsRepository {

    override suspend fun fetchBrands(): Flow<PagingData<Brand>> =
        withContext(Dispatchers.IO) {
            Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
                BrandSource(remoteDataSource)
            }.flow
        }

    override suspend fun fetchModels(id: String?): Flow<PagingData<Model>> =
        withContext(Dispatchers.IO) {
            Pager(PagingConfig(pageSize = NETWORK_PAGE_SIZE)) {
                ModelSource(id, remoteDataSource)
            }.flow
        }

    override suspend fun fetchYears(id: String?, name: String?): Flow<List<Year>> =
        withContext(Dispatchers.IO) {
            val response = remoteDataSource.fetchYears(id, name)
            val years: Flow<List<Year>> = flow {
                emit(response.years.map { it.toYear() })
            }
            years
        }
}