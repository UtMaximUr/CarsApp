package com.eg.cars.data


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eg.cars.data.repository.RemoteDataSource
import com.eg.cars.domain.entity.Model
import java.io.IOException

class ModelSource(
    private val id: String?,
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Model>() {

    override fun getRefreshKey(state: PagingState<Int, Model>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Model> {
        return try {
            val nextPage = params.key ?: 1
            val response = remoteDataSource.fetchModels(id, nextPage)
            val models = response.models.map { it.toModel() }
            LoadResult.Page(
                data = models,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (models.isEmpty()) null else response.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}