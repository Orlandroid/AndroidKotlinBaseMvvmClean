package com.example.androidbase.presentation

import org.gradle.api.artifacts.dsl.DependencyHandler


object Dependencies {
    const val NAVIGATION_VERSION = "2.7.5"
    const val ANDROID_MATERIAL = "com.google.android.material:material:1.11.0"
    const val ANDROIDX_ACTIVITY = "androidx.activity:activity-ktx:1.8.2"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:1.6.1"
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:1.12.0"
    const val ANDROIDX_FRAGMENT = "androidx.fragment:fragment-ktx:1.6.2"
    const val ANDROIDX_LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
    const val ANDROIDX_LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2"
    const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:2.6.2"
    const val CARDVIEW = "androidx.cardview:cardview:1.0.0"
    const val DRAWER_LAYOUT = "androidx.drawerlayout:drawerlayout:1.2.0"
    const val DAGGER_HILT = "com.google.dagger:hilt-android:2.46.1"
    const val DAGGER_HILT_COMPILER = "androidx.hilt:hilt-compiler:1.1.0"
    const val DAGGER_HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:2.46.1"
    const val GLIDE = "com.github.bumptech.glide:glide:4.16.0"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:4.16.0"
    const val KOTLIN_STDLIB_JDK7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.31"
    const val KOTLINX_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9"
    const val MOSHI = "com.squareup.moshi:moshi-kotlin:1.11.0"
    const val MOSHI_ADAPTERS = "com.squareup.moshi:moshi-adapters:1.11.0"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:1.11.0"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"
    const val PAGING = "androidx.paging:paging-runtime:3.2.1"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:3.0.0-beta02"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val RETROFIT_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
    const val RETROFIT_CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:2.9.0"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val ROOM = "androidx.room:room-runtime:2.6.1"
    const val ROOM_COMPILER = "androidx.room:room-compiler:2.6.1"
    const val ROOM_KOTLIN_EXTENSION = "androidx.room:room-ktx:2.6.1"
    const val TEST_JUNIT = "androidx.test.ext:junit:1.1.4"
    const val JUNIT_JUNIT = "junit:junit:4.13.2"
    const val TEST_EXPRESO = "androidx.test.espresso:espresso-core:3.5.0"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.VIEW_MODEL)
    implementation(Dependencies.LIVE_DATA)
    implementation(Dependencies.ANDROIDX_FRAGMENT)
}

fun DependencyHandler.room() {
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KOTLIN_EXTENSION)
    kapt(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.RETROFIT_CONVERTER_MOSHI)
    implementation(Dependencies.RETROFIT_INTERCEPTOR)
}

fun DependencyHandler.daggerHilt() {
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_ANDROID_COMPILER)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
}

fun DependencyHandler.navigationComponent() {
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
}

fun DependencyHandler.glide() {
    implementation(Dependencies.GLIDE)
    annotationProcessor(Dependencies.GLIDE_COMPILER)
}

