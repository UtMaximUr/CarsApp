package com.eg.cars.feature_year

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eg.cars.domain.entity.Cars
import com.eg.cars.domain.entity.Year
import com.eg.cars.domain.use_case.GetYearsUseCase
import com.eg.cars.utils.setPostValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearViewModel @Inject constructor(private val useCase: GetYearsUseCase) : ViewModel() {


    val years: LiveData<List<Year>> by lazy {
        MutableLiveData()
    }

    fun fetchYear(cars: Cars?) = viewModelScope.launch {
        years.setPostValue(useCase.invoke(cars?.id, cars?.model))
    }
}