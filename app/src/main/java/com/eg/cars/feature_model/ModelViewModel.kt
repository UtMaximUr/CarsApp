package com.eg.cars.feature_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eg.cars.data.Resource
import com.eg.cars.domain.entity.Cars
import com.eg.cars.domain.entity.Model
import com.eg.cars.domain.use_case.GetModelUseCase
import com.eg.cars.main.base.BaseViewModel
import com.eg.cars.utils.setPostValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModelViewModel @Inject constructor(private val useCase: GetModelUseCase) : BaseViewModel() {

    val models: Flow<PagingData<Model>> by lazy { MutableSharedFlow(replay = 1) }

    override val progressState: LiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    override val errorState: LiveData<String> by lazy {
        MutableLiveData()
    }

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