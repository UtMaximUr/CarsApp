import com.eg.buildsrc.Base
import com.eg.buildsrc.BuildPlugins
import com.eg.buildsrc.Libs
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Base.compileSdk

    defaultConfig {
        minSdk = Base.minSdk
        targetSdk = Base.targetSdk
        version = Base.versionName
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
    implementation(project(":core"))
    implementation(Libs.Hilt.hilt_android)
    kapt(Libs.Hilt.hilt_compiler)
}