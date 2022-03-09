package com.eg.buildsrc

object BuildPlugins {

    const val compose_version = "1.2.0-alpha02"
    private const val kotlin_version = "1.6.10"
    private const val gradle_version = "7.0.4"
    private const val hilt_version = "2.40.5"

    const val gradle = "com.android.tools.build:gradle:$gradle_version"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    const val hilt =  "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
}