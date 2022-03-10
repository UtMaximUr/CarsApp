package com.eg.feature_year

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eg.domain.entity.Cars
import com.eg.domain.entity.Year
import com.eg.domain.use_case.GetYearsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearViewModel @Inject constructor(private val useCase: GetYearsUseCase) : ViewModel() {

    val years: Flow<List<Year>> by lazy { MutableSharedFlow(replay = 1) }

    fun fetchYear(cars: Cars?) = viewModelScope.launch {
        useCase.invoke(cars?.id, cars?.model).collect {
            (years as MutableSharedFlow).emit(it)
        }
    }
}