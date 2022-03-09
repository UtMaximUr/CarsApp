package com.eg.cars.data.assets

import android.content.res.AssetManager
import java.io.IOException

class AssetManager(private val assetManager: AssetManager) {

    fun getBrandsList(): String {
        return getJsonFromAssets()
    }

    private fun getJsonFromAssets(): String {
        var jsonString = ""
        try {
            val inputStream = assetManager.open("brands_list.json")
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