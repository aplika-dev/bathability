plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.aplika.bathability"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.aplika.bathability"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    rootProject.subprojects.forEach { project ->
        if (project.subprojects.isEmpty() && !project.plugins.hasPlugin(com.android.build.gradle.AppPlugin::class)) {
            implementation(project)
        }
    }

    implementation(libs.activity.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}