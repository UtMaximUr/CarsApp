package com.eg.feature_year

import androidx.lifecycle.viewModelScope
import com.eg.cars.main.base.BaseViewModel
import com.eg.core.utils.setPostValue
import com.eg.domain.entity.Cars
import com.eg.domain.entity.Resource
import com.eg.domain.entity.Year
import com.eg.domain.use_case.GetYearsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearViewModel @Inject constructor(private val useCase: GetYearsUseCase) : BaseViewModel() {

    val years: Flow<List<Year>> by lazy { MutableSharedFlow(replay = 1) }

    fun fetchYear(cars: Cars?) = viewModelScope.launch {
        val resource = useCase.invoke(cars?.id, cars?.model)
        when (resource.status) {
            Resource.Status.SUCCESS -> {
                progressState.setPostValue(false)
                resource.data?.collect {
                    (years as MutableSharedFlow).emit(it)
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