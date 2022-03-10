package com.eg.feature_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eg.domain.entity.Cars
import com.eg.domain.entity.Model
import com.eg.domain.use_case.GetModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModelViewModel @Inject constructor(private val useCase: GetModelUseCase) : ViewModel() {

    val models: Flow<PagingData<Model>> by lazy { MutableSharedFlow(replay = 1) }

    fun fetchModel(cars: Cars?) = viewModelScope.launch {
        useCase.invoke(cars?.id).cachedIn(viewModelScope).collect {
            (models as MutableSharedFlow).emit(it)
        }
    }
}