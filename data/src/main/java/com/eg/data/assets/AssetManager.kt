package com.eg.data.assets

import android.content.res.AssetManager
import com.eg.data.network.entity.CarsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class AssetManager(private val assetManager: AssetManager) {

    fun getCarsList(): String {
        return getJsonFromAssets()
    }

    private fun getJsonFromAssets(): String {
        var jsonString = ""
        try {
            val inputStream = assetManager.open("cars_list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
        }
        return jsonString
    }
}