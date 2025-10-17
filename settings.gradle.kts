pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://nexus.fjruiz.duckdns.org/nexus/content/repositories/releases")
        google()
        mavenCentral()
    }
}

rootProject.name = "MagicTimerApp"
include(":app")
include(":domain")
include(":data")
include(":components")
