package com.eg.data.network

import com.eg.data.assets.AssetManager
import com.eg.data.network.entity.BrandResponse
import com.eg.data.network.entity.CarsResponse
import com.eg.data.network.entity.ModelsResponse
import com.eg.data.network.entity.YearsResponse
import com.eg.data.utils.API_PATH
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import timber.log.Timber
import java.util.concurrent.TimeUnit


class ApiDataSource(private val assetManager: AssetManager) {

    private val server = MockWebServer()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .build()

    fun loadBrands(page: Int): BrandResponse {
        server.enqueue(MockResponse().setBody(assetManager.getBrandsList()))

        val request = request(
            server.url(API_PATH)
                .newBuilder()
                .addPathSegment("brands")
                .addQueryParameter("page", page.toString())
                .build()
        )

        Timber.d("request.url brands -> ${request.url}")

        val cars = Gson().fromJson(readBody(request), CarsResponse::class.java)
        return BrandResponse(page = page, brands = cars.brands)
    }

    fun loadModels(id: String?, page: Int): ModelsResponse {
        server.enqueue(MockResponse().setBody(assetManager.getBrandsList()))

        val request = request(
            server.url(API_PATH).newBuilder()
                .addPathSegment("models")
                .addQueryParameter("id", id)
                .addQueryParameter("page", page.toString())
                .build()
        )

        Timber.d("request.url models -> ${request.url}")

        val brand = Gson().fromJson(
            readBody(request),
            CarsResponse::class.java
        ).brands.first { it.id == id }
        return ModelsResponse(page = page, models = brand.models)
    }

    fun loadYears(id: String?, name: String?): YearsResponse {
        server.enqueue(MockResponse().setBody(assetManager.getBrandsList()))

        val request = request(
            server.url(API_PATH).newBuilder()
                .addPathSegment("years")
                .addQueryParameter("id", id)
                .addQueryParameter("name", name)
                .build()
        )

        Timber.d("request.url years -> ${request.url}")

        val brand = Gson().fromJson(
            readBody(request),
            CarsResponse::class.java
        ).brands.first { it.id == id }
        val models = brand.models.first { it.name == name }
        return YearsResponse(years = models.years)
    }

    private fun request(url: HttpUrl): Request {
        return Request.Builder()
            .url(url)
            .get()
            .header("Authorization", "xxx")
            .header("Content-Type", "application/json; charset=utf-8").build()
    }

    private fun readBody(request: Request): String? {
        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful) {
            return response.body?.string()
        }
        return response.message
    }
}