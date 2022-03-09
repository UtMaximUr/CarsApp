package com.eg.feature_brand

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    override val progressState: LiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    override val errorState: LiveData<String> by lazy {
        MutableLiveData()
    }

    fun fetchBrands() = viewModelScope.launch {
        val resource = useCase.invoke()
        when (resource.status) {
            Resource.Status.LOADING -> {
                progressState.setPostValue(resource.data == null)
            }
            Resource.Status.SUCCESS -> {
                progressState.setPostValue(resource.data == null)
                resource.data?.cachedIn(viewModelScope)?.collect {
                    (brands as MutableSharedFlow).emit(it)
                }
            }
            Resource.Status.ERROR -> {
                progressState.setPostValue(resource.data == null)
                errorState.setPostValue(resource.message.toString())
            }
        }
    }
}