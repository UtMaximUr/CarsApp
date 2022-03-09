package com.eg.domain.use_case

import com.eg.domain.CarsRepository
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    suspend operator fun invoke() = carsRepository.fetchBrands()

}