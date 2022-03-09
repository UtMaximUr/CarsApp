package com.eg.cars.utils

import android.net.Uri
import com.eg.cars.domain.entity.Cars
import com.google.gson.Gson

fun Cars?.toJson(): String? = Uri.encode(Gson().toJson(this))