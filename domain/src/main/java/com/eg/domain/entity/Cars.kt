package com.eg.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cars(
    val id: String? = null,
    val brand: String? = null,
    val model: String? = null,
    val year: String? = null
) : Parcelable
