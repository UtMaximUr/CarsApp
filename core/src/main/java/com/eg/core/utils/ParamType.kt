package com.eg.core.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.eg.domain.entity.Cars
import com.google.gson.Gson

class ParamType : NavType<Cars>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Cars? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Cars {
        return Gson().fromJson(value, Cars::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Cars) {
        bundle.putParcelable(key, value)
    }
}