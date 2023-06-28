pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Bathability"
include(":app")
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:domain")
include(":core:resources")
include(":core:android")
include(":core:kotlin")
include(":core:navigation")
include(":core:datastore")
include(":features:map")
