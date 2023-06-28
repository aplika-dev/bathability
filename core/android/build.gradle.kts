plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.aplika.core.android"
    compileSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(project(":core:kotlin"))

    api(platform(libs.firebase.bom))

    implementation(libs.firebase.crashlytics)
    api(libs.coroutines.android)

    api(libs.lifecycle.viewmodel.compose)
    api(libs.lifecycle.runtime)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}