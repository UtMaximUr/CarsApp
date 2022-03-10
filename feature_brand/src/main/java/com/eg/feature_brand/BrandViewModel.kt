package com.eg.feature_brand

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eg.domain.entity.Resource
import com.eg.domain.entity.Brand
import com.eg.domain.use_case.GetBrandsUseCase
import com.eg.cars.main.base.BaseViewModel
import com.eg.core.utils.setPostValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandViewModel @Inject constructor(private val useCase: GetBrandsUseCase) : BaseViewModel() {

    val brands: Flow<PagingData<Brand>> by lazy { MutableSharedFlow(replay = 1) }

    fun fetchBrands() = viewModelScope.launch {
        val resource = useCase.invoke()
        when (resource.status) {
            Resource.Status.SUCCESS -> {
                progressState.setPostValue(false)
                resource.data?.cachedIn(viewModelScope)?.collect {
                    (brands as MutableSharedFlow).emit(it)
                }
            }
            Resource.Status.ERROR -> {
                progressState.setPostValue(false)
                errorState.setPostValue(resource.message.toString())
            }
            else -> {}
        }
    }
}