package com.eg.data.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eg.data.network.entity.BrandResponse
import com.eg.data.repository.RemoteDataSource
import com.eg.data.utils.NETWORK_PAGE_SIZE
import com.eg.domain.entity.Brand
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class BrandSource(
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Brand>() {

    override fun getRefreshKey(state: PagingState<Int, Brand>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Brand> =
        withContext(Dispatchers.IO) {
            try {
                val nextPage = params.key ?: 1
                val response = remoteDataSource.fetchBrands(nextPage, NETWORK_PAGE_SIZE)
                val brandsResponse =
                    Gson().fromJson(response.body?.string(), BrandResponse::class.java)
                val brands = brandsResponse.brands.map { it.toBrand() }
                LoadResult.Page(
                    data = brands,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (brands.isEmpty()) null else brandsResponse.page + 1
                )
            } catch (exception: IOException) {
                LoadResult.Error(exception)
            }
        }
}