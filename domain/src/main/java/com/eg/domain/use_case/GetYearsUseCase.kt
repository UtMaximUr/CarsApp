package com.eg.domain.use_case


import com.eg.domain.CarsRepository
import javax.inject.Inject

class GetYearsUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    suspend operator fun invoke(id: String?, name: String?) = carsRepository.fetchYears(id, name)

}