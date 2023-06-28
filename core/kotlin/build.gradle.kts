plugins {
    id(libs.plugins.javaLibrary.get().pluginId)
    alias(libs.plugins.kotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api(libs.coroutines.core)
}