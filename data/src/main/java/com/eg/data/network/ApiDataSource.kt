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
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import timber.log.Timber
import java.util.concurrent.TimeUnit


class ApiDataSource(private val assetManager: AssetManager) {

    private val server = MockWebServer()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    fun loadBrands(page: Int, pageSize: Int): Response {
        val cars = Gson().fromJson(assetManager.getCarsList(), CarsResponse::class.java)
        val brands = Gson().toJson(BrandResponse(page, cars.brands))
        server.enqueue(MockResponse().setBodyDelay(2, TimeUnit.SECONDS).setBody(brands))

        val request = request(
            server.url(API_PATH)
                .newBuilder()
                .addPathSegment("brands")
                .addQueryParameter("page", page.toString())
                .addQueryParameter("pageSize", pageSize.toString())
                .build()
        )

        Timber.d("request.url brands -> ${request.url}")

        return okHttpClient.newCall(request).execute()
    }

    fun loadModels(id: String?, page: Int, pageSize: Int): Response {

        val cars = Gson().fromJson(assetManager.getCarsList(), CarsResponse::class.java)
        val brand = cars.brands.first { it.id == id }

        val models = Gson().toJson(ModelsResponse(page, brand.models))
        server.enqueue(MockResponse().setBody(models))

        val request = request(
            server.url(API_PATH).newBuilder()
                .addPathSegment("models")
                .addQueryParameter("id", id)
                .addQueryParameter("page", page.toString())
                .addQueryParameter("pageSize", pageSize.toString())
                .build()
        )

        Timber.d("request.url models -> ${request.url}")

        return okHttpClient.newCall(request).execute()
    }

    fun loadYears(id: String?, name: String?): Response {

        val cars = Gson().fromJson(assetManager.getCarsList(), CarsResponse::class.java)
        val brand = cars.brands.first { it.id == id }
        val model = brand.models.first { it.name == name }

        val years = Gson().toJson(YearsResponse(model.years))
        server.enqueue(MockResponse().setBody(years))

        val request = request(
            server.url(API_PATH).newBuilder()
                .addPathSegment("years")
                .addQueryParameter("id", id)
                .addQueryParameter("name", name)
                .build()
        )

        Timber.d("request.url years -> ${request.url}")

        return okHttpClient.newCall(request).execute()
    }

    private fun request(url: HttpUrl): Request {
        return Request.Builder()
            .url(url)
            .get()
            .header("Authorization", "xxx")
            .header("Content-Type", "application/json; charset=utf-8").build()
    }
}