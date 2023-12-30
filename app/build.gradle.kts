plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "dev.aplika.bathability"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.aplika.bathability"
        minSdk = 24
        targetSdk = 34
        versionCode = 4
        versionName = "1.0.3"

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
        buildConfig = true
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

    implementation(libs.firebase.cloud.messaging)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}