plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "dev.aplika.data.collect_point"
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

    api(project(":core:datastore"))
    api(project(":core:database"))

    implementation(project(":network:santa_catarina"))
    implementation(project(":network:rio_grande_do_sul"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}