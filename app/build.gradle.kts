import com.eg.buildsrc.Base
import com.eg.buildsrc.BuildPlugins
import com.eg.buildsrc.Libs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Base.compileSdk

    defaultConfig {
        applicationId = Base.applicationId
        minSdk = Base.minSdk
        targetSdk = Base.targetSdk
        versionCode = Base.versionCode
        versionName = Base.versionName

        testInstrumentationRunner = Base.androidTestInstrumentation

        vectorDrawables {
            useSupportLibrary = true
        }
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
        freeCompilerArgs = listOf("-Xjvm-default=compatibility", "-Xopt-in=kotlin.RequiresOptIn")
        jvmTarget = Base.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildPlugins.compose_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":feature_brand"))
    implementation(project(":feature_model"))
    implementation(project(":feature_year"))
    implementation(project(":feature_info"))

    implementation(Libs.Core.appcompat)
    implementation(Libs.Core.material)

    implementation(Libs.Compose.activity)

    implementation(Libs.Hilt.hilt_android)
    kapt(Libs.Hilt.hilt_compiler)

    implementation(Libs.Timber.timber)
}