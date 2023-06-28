plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlinKapt) apply false
}
true // Needed to make the Suppress annotation work for the plugins block