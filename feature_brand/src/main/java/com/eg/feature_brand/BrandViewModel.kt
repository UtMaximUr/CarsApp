package com.eg.feature_brand

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eg.domain.entity.Brand
import com.eg.domain.use_case.GetBrandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandViewModel @Inject constructor(private val useCase: GetBrandsUseCase) : ViewModel() {

    val brands: Flow<PagingData<Brand>> by lazy { MutableSharedFlow(replay = 1) }

    fun fetchBrands() = viewModelScope.launch {
        useCase.invoke().cachedIn(viewModelScope).collect {
            (brands as MutableSharedFlow).emit(it)
        }
    }
}