import com.eg.buildsrc.Base
import com.eg.buildsrc.Libs

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Base.compileSdk

    defaultConfig {
        minSdk = Base.minSdk
        targetSdk = Base.targetSdk
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Libs.Core.coreKtx)

    implementation(Libs.Json.gson)

    implementation(Libs.Hilt.hilt_android)
    kapt(Libs.Hilt.hilt_compiler)

    implementation(Libs.Paging.paging)

    implementation(Libs.Timber.timber)

    implementation(Libs.Mock.okhttp)
    implementation(Libs.Mock.mockwebserver)
}