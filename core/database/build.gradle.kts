plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "dev.aplika.core.database"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    api(project(":core:android"))
    api(project(":core:domain"))

    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}