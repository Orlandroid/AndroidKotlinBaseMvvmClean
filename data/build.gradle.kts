import com.example.androidbase.presentation.Dependencies
import com.example.androidbase.presentation.BuildModules
import com.example.androidbase.presentation.ConfigData
import com.example.androidbase.presentation.daggerHilt
import com.example.androidbase.presentation.retrofit
import com.example.androidbase.presentation.room

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.data"
    compileSdk = ConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = ConfigData.MIN_SDK_VERSION
        targetSdk = ConfigData.TARGET_SDK_VERSION

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(BuildModules.DOMAIN))
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.ANDROID_MATERIAL)
    testImplementation(Dependencies.JUNIT_JUNIT)
    androidTestImplementation(Dependencies.TEST_JUNIT)
    androidTestImplementation(Dependencies.TEST_EXPRESO)
    //Retrofit Dependecies
    retrofit()
    //Room
    room()
    //Dagger - Hilt
    daggerHilt()
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

}