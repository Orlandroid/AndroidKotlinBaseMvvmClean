import com.example.androidbase.presentation.BuildModules
import com.example.androidbase.presentation.ConfigData
import com.example.androidbase.presentation.Libs
import com.example.androidbase.presentation.daggerHilt
import com.example.androidbase.presentation.glide
import com.example.androidbase.presentation.navigationComponent
import com.example.androidbase.presentation.retrofit
import com.example.androidbase.presentation.room

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.presentation"
    compileSdk = ConfigData.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = ConfigData.APPLICATION_ID
        minSdk = ConfigData.MIN_SDK_VERSION
        targetSdk = ConfigData.TARGET_SDK_VERSION
        versionCode = ConfigData.VERSION_CODE
        versionName = ConfigData.VERSION_NAME

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
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
    implementation(project(BuildModules.DATA))
    implementation(project(BuildModules.DOMAIN))
    implementation(Libs.Android.ANDROIDX_CORE_KTX)
    implementation(Libs.Android.ANDROIDX_APPCOMPAT)
    implementation(Libs.Google.ANDROID_MATERIAL)
    implementation(Libs.Android.ANDROIDX_CONSTRAINT_LAYOUT)
    testImplementation(Libs.Testing.JUNIT_JUNIT)
    androidTestImplementation(Libs.Testing.TEST_JUNIT)
    androidTestImplementation(Libs.Testing.TEST_EXPRESO)
    //Navigation component
    navigationComponent()
    //Dagger - Hilt
    daggerHilt()
    //Retrofit Dependecies
    retrofit()
    //lifecycle
    implementation(Libs.Android.VIEW_MODEL)
    implementation(Libs.Android.LIVE_DATA)
    implementation(Libs.Android.ANDROIDX_FRAGMENT)
    //Room
    room()
    //Glide
    glide()
    implementation(Libs.Android.SWIPE_REFRESH_LAYOUT)
    //implementation("com.github.Spikeysanju:MotionToast:1.4")
    implementation("com.faltenreich:skeletonlayout:5.0.0")
}