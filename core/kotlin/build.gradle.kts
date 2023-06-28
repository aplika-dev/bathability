plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(libs.coroutines.core)
}