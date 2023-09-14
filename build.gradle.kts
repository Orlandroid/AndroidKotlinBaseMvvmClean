buildscript {
    val kotlin_version by extra("1.8.20")
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        val navigationVersion = "2.5.3"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.20" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
