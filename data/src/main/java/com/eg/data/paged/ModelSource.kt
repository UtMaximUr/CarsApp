package com.eg.data.paged


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eg.data.network.entity.ModelsResponse
import com.eg.data.repository.RemoteDataSource
import com.eg.data.utils.NETWORK_PAGE_SIZE
import com.eg.domain.entity.Model
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ModelSource(
    private val id: String?,
    private val remoteDataSource: RemoteDataSource,
) : PagingSource<Int, Model>() {

    override fun getRefreshKey(state: PagingState<Int, Model>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Model> =
        withContext(Dispatchers.IO) {
            try {
                val nextPage = params.key ?: 1
                val response = remoteDataSource.fetchModels(id, nextPage, NETWORK_PAGE_SIZE)
                val modelsResponse = Gson()
                    .fromJson(response.body?.string(), ModelsResponse::class.java)
                val models = modelsResponse.models.map { it.toModel() }
                LoadResult.Page(
                    data = models,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (models.isEmpty()) null else modelsResponse.page + 1
                )
            } catch (exception: IOException) {
                LoadResult.Error(exception)
            }
        }
}