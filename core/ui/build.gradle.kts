plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.aplika.core.ui"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":core:navigation"))
    api(project(":core:resources"))
    api(project(":core:domain"))

    api(platform(libs.compose.bom))
    api(libs.compose.runtime.livedata)
    api(libs.compose.material3)
    api(libs.compose.ui.tooling)
    api(libs.androidx.hilt.navigation.compose)
    api(libs.navigation.compose)

    api(libs.lifecycle.viewmodel.compose)
}