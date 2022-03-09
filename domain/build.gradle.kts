import com.eg.buildsrc.Base
import com.eg.buildsrc.Libs

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = Base.compileSdk

    defaultConfig {
        minSdk = Base.minSdk
        targetSdk = Base.targetSdk
    }
}

dependencies {
    implementation(Libs.Core.coroutines)
    implementation(Libs.Hilt.hilt_android)
    kapt(Libs.Hilt.hilt_compiler)

    implementation(Libs.Paging.paging)
}