plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")

}

android {
    namespace = "com.example.androidbase"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.androidbase"
        minSdk = 22
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


}

dependencies {
    implementation(Dependencies.ANDROIDX_LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation project (":data")
    implementation project (":domain")
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.7.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.4'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0'
    //Navigation component
    implementation "androidx.navigation:navigation-fragment-ktx:$navigation_version"
    implementation "androidx.navigation:navigation-ui-ktx:$navigation_version"
    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:$dagger_hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:$dagger_hilt_version"
    kapt 'androidx.hilt:hilt-compiler:1.0.0'
    //Retrofit Dependecies
    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
    implementation "com.squareup.retrofit2:converter-moshi:$retrofit_version"
    implementation "com.squareup.okhttp3:logging-interceptor:4.9.2"
    //lifecycle
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    implementation 'androidx.fragment:fragment-ktx:1.5.5'
    //Room
    implementation "androidx.room:room-runtime:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    //Glide
    implementation 'com.github.bumptech.glide:glide:4.13.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.13.2'
}