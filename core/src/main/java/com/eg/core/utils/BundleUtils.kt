package com.eg.core.utils

import android.net.Uri
import com.eg.domain.entity.Cars
import com.google.gson.Gson

fun Cars?.toJson(): String? = Uri.encode(Gson().toJson(this))