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
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:resources")
include(":core:android")
include(":core:kotlin")
include(":core:navigation")
include(":core:datastore")
include(":core:ui")

include(":feature:map")
include(":feature:collect_point_detailed")
include(":feature:menu")
include(":feature:about")

include(":network:santa_catarina")
include(":network:rio_grande_do_sul")

include(":data:collect_point")
include(":data:collect")
include(":data:onboarding")
