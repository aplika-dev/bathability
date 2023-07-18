plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "dev.aplika.core.network"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        buildConfig = true
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

    flavorDimensions.add("env")
    productFlavors {
        create("dev")
        create("prod")
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

    api(libs.retrofit)
    api(libs.kotlinx.serialization)
    api(libs.retrofit.kotlinx.serialization)

    api(platform(libs.okhttp.bom))
    api(libs.okhttp)
    api(libs.okhttp.logging.interceptor)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}