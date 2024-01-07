import com.example.androidbase.presentation.ConfigData
import com.example.androidbase.presentation.Dependencies
import com.example.androidbase.presentation.room

plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.domain"
    compileSdk = ConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = ConfigData.MIN_SDK_VERSION
        targetSdk = ConfigData.TARGET_SDK_VERSION

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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
}

dependencies {

    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.ANDROID_MATERIAL)
    testImplementation(Dependencies.JUNIT_JUNIT)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    room()
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
}