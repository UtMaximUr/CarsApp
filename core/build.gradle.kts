import com.eg.buildsrc.Base
import com.eg.buildsrc.BuildPlugins
import com.eg.buildsrc.Libs

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Base.compileSdk

    defaultConfig {
        minSdk = Base.minSdk
        targetSdk = Base.targetSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Base.proguardOptimize), Base.proguardRulesPro
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = listOf(Base.xJvm, Base.xOpt)
        jvmTarget = Base.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildPlugins.compose_version
    }
}

dependencies {
    api(project(":domain"))

    api(Libs.Core.coreKtx)
    api(Libs.Compose.material)

    api(Libs.Hilt.hilt_compose)
    api(Libs.Compose.livedata)
    api(Libs.Navigation.navigation_compose)

    api(Libs.Json.gson)
    api(Libs.Paging.paging)
}