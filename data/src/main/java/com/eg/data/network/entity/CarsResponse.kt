package com.eg.data.network.entity

import com.eg.domain.entity.Brand
import com.eg.domain.entity.Model
import com.eg.domain.entity.Year


data class CarsResponse(
    val page: Int,
    val brands: List<BrandEntity>
)

data class BrandEntity(
    val id: String,
    val name: String,
    val models: List<ModelEntity>
) {
    fun toBrand() = Brand(
        id = id,
        name = name
    )
}

data class ModelEntity(
    val name: String,
    val years: List<YearEntity>
) {
    fun toModel() = Model(
        name = name
    )
}

data class YearEntity(
    val year: String
) {
    fun toYear() = Year(
        year = year
    )
}