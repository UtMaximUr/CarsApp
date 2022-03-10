package com.eg.feature_model

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eg.cars.main.base.BaseViewModel
import com.eg.core.utils.setPostValue
import com.eg.domain.entity.Cars
import com.eg.domain.entity.Model
import com.eg.domain.entity.Resource
import com.eg.domain.use_case.GetModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModelViewModel @Inject constructor(private val useCase: GetModelUseCase) : BaseViewModel() {

    val models: Flow<PagingData<Model>> by lazy { MutableSharedFlow(replay = 1) }

    fun fetchModel(cars: Cars?) = viewModelScope.launch {
        val resource = useCase.invoke(cars?.id)
        when (resource.status) {
            Resource.Status.LOADING -> {
                progressState.setPostValue(resource.data == null)
            }
            Resource.Status.SUCCESS -> {
                progressState.setPostValue(resource.data == null)
                resource.data?.cachedIn(viewModelScope)?.collect {
                    (models as MutableSharedFlow).emit(it)
                }
            }
            Resource.Status.ERROR -> {
                progressState.setPostValue(resource.data == null)
                errorState.setPostValue(resource.message.toString())
            }
        }
    }
}