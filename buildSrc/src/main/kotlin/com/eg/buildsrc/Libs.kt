package com.eg.buildsrc

object Libs {

    object Core {
        private const val core_ktx = "1.7.0"
        private const val appcompat_version = "1.4.1"
        private const val material_version = "1.5.0"
        private const val coroutines_version = "1.3.9"

        const val coreKtx = "androidx.core:core-ktx:$core_ktx"
        const val appcompat = "androidx.appcompat:appcompat:$appcompat_version"
        const val material = "com.google.android.material:material:$material_version"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    }

    object Compose {
        private const val compose_version = "1.2.0-alpha02"
        private const val activity_version = "1.4.0"

        const val ui = "androidx.compose.ui:ui:$compose_version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$compose_version"
        const val runtime = "androidx.compose.runtime:runtime:$compose_version"
        const val material = "androidx.compose.material:material:$compose_version"
        const val activity = "androidx.activity:activity-compose:$activity_version"
        const val livedata = "androidx.compose.runtime:runtime-livedata:$compose_version"
    }

    object Navigation {
        private const val nav_version = "2.5.0-alpha01"
        const val navigation_compose = "androidx.navigation:navigation-compose:$nav_version"
    }

    object Hilt {
        private const val hilt_version = "2.40.5"
        private const val hilt_compose_version = "1.0.0"
        const val hilt_android = "com.google.dagger:hilt-android:$hilt_version"
        const val hilt_compiler = "com.google.dagger:hilt-compiler:$hilt_version"
        const val hilt_compose = "androidx.hilt:hilt-navigation-compose:$hilt_compose_version"
    }

    object Json {
        private const val gson_version = "2.8.9"
        const val gson = "com.google.code.gson:gson:$gson_version"
    }

    object Paging {
        private const val paging_version = "1.0.0-alpha14"
        const val paging = "androidx.paging:paging-compose:$paging_version"
    }

    object Mock {
        private const val mock_version = "5.0.0-alpha.5"
        const val okhttp = "com.squareup.okhttp3:okhttp:$mock_version"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:$mock_version"
    }

    object Timber {
        private const val timber_version = "5.0.1"
        const val timber = "com.jakewharton.timber:timber:$timber_version"
    }
}


