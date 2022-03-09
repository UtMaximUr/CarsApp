package com.eg.cars.domain.use_case

import com.eg.cars.data.repository.CarsRepositoryImpl
import javax.inject.Inject

class GetYearsUseCase @Inject constructor(private val carsRepository: CarsRepositoryImpl) {

    suspend operator fun invoke(id: String?, name: String?) = carsRepository.fetchYears(id, name)

}