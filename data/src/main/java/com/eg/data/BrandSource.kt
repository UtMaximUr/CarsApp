package com.eg.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eg.data.repository.RemoteDataSource
import com.eg.domain.entity.Brand
import java.io.IOException

class BrandSource(
    private val remoteDataSource: RemoteDataSource,
    private val pageSize: Int
) : PagingSource<Int, Brand>() {

    override fun getRefreshKey(state: PagingState<Int, Brand>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Brand> {
        return try {
            val nextPage = params.key ?: 1
            params.loadSize
            val response = remoteDataSource.fetchBrands(nextPage, pageSize)
            val brands = response.brands.map { it.toBrand() }
            LoadResult.Page(
                data = brands,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (brands.isEmpty()) null else response.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}